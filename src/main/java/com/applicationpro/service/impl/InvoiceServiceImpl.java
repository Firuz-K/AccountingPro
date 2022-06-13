package com.applicationpro.service.impl;

import com.applicationpro.dto.ClientVendorDTO;
import com.applicationpro.dto.InvoiceDTO;
import com.applicationpro.dto.common.TotalInvoiceDto;
import com.applicationpro.entity.Invoice;
import com.applicationpro.entity.InvoiceProduct;
import com.applicationpro.entity.Product;
import com.applicationpro.entity.Stock;
import com.applicationpro.enums.InvoiceStatus;
import com.applicationpro.enums.InvoiceType;
import com.applicationpro.enums.ProductStatus;
import com.applicationpro.repository.InvoiceProductRepository;
import com.applicationpro.repository.InvoiceRepository;
import com.applicationpro.repository.ProductRepository;
import com.applicationpro.repository.StockRepository;
import com.applicationpro.service.InvoiceProductService;
import com.applicationpro.service.InvoiceService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private final ProductRepository productRepository;
    private final InvoiceProductService invoiceProductService;
    private final StockRepository stockRepository;

    private final MapperUtil mapperUtil;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceProductRepository invoiceProductRepository, ProductRepository productRepository, InvoiceProductService invoiceProductService, StockRepository stockRepository, MapperUtil mapperUtil) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.productRepository = productRepository;
        this.invoiceProductService = invoiceProductService;
        this.stockRepository = stockRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceDTO> listAllInvoices() {
        cleanCancelledInvoices();
        return invoiceRepository.findAll().stream()
                .map(invoice -> mapperUtil.convert(invoice, new InvoiceDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> listAllNonDraftInvoicesOrderByDate() {
        cleanCancelledInvoices();
        return invoiceRepository.findAllByInvoiceStatusIsNotOrderByInvoiceDate(InvoiceStatus.DRAFT).stream()
                .map(invoice -> mapperUtil.convert(invoice, new InvoiceDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TotalInvoiceDto> listAllTotalInvoices(InvoiceType invoiceType) {
        cleanCancelledInvoices();
        List<TotalInvoiceDto> totalInvoiceList = new ArrayList<>();
        for (Invoice invoice : invoiceRepository.findAllByInvoiceType(invoiceType)) {
            TotalInvoiceDto totalInvoice = new TotalInvoiceDto();
            totalInvoice.setInvoiceDto(mapperUtil.convert(invoice, new InvoiceDTO()));
            totalInvoice.setTotalAmount(calculateTotalInvoiceAmount(invoice));
            totalInvoice.setTotalTax(calculateTotalInvoiceTax(invoice));
            totalInvoiceList.add(totalInvoice);
        }
        return totalInvoiceList;
    }

    @Override
    public TotalInvoiceDto findTotalInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.getById(id);
        TotalInvoiceDto totalInvoice = new TotalInvoiceDto();
        totalInvoice.setInvoiceDto(mapperUtil.convert(invoice, new InvoiceDTO()));
        totalInvoice.setTotalAmount(calculateTotalInvoiceAmount(invoice));
        totalInvoice.setTotalTax(calculateTotalInvoiceTax(invoice));
        return totalInvoice;
    }

    @Override
    public List<InvoiceDTO> listAllInvoices(InvoiceType invoiceType) {
        cleanCancelledInvoices();
        return invoiceRepository.findAllByInvoiceType(invoiceType).stream()
                .map(invoice -> mapperUtil.convert(invoice, new InvoiceDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO findInvoiceById(Long id) {
        return mapperUtil.convert(invoiceRepository.findById(id), new InvoiceDTO());
    }

    @Override
    @Transactional
    public void approve(Long id) throws Exception {
        try {
            Invoice invoice = invoiceRepository.getById(id);
            invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
            List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoice(invoice);

            for (InvoiceProduct invoiceProduct : invoiceProductList) {

                // Update PRODUCT repository
                Product product = productRepository.getById(invoiceProduct.getProduct().getId());
                if (invoice.getInvoiceType().equals(InvoiceType.PURCHASE)) {
                    product.setQuantity(product.getQuantity() + invoiceProduct.getQuantity());
                } else {
                    // If it is a SALE Invoice, first check inventory
                    if (product.getQuantity() < invoiceProduct.getQuantity())
                        throw new Exception("Product id: " + product.getId() + " name: " + product.getName() + " is out of stock !!!");
                    product.setQuantity(product.getQuantity() - invoiceProduct.getQuantity());
                }

                // Update ProductStatus based on stock
                if (product.getQuantity() <= 0) {
                    product.setProductStatus(ProductStatus.OUT_OF_STOCK);
                } else {
                    product.setProductStatus(ProductStatus.ACTIVE);
                }
                product.setTax(invoiceProduct.getTax());
                productRepository.save(product);

                // Update STOCK repository
                Stock stock = new Stock();
                stock.setInvoiceDate(invoice.getInvoiceDate().atTime(LocalTime.now()));
                stock.setQuantity(invoiceProduct.getQuantity());
                stock.setProduct(invoiceProduct.getProduct());
                stock.setRemainingQuantity(invoiceProduct.getQuantity());
                stock.setPrice(invoiceProduct.getPrice());
                stock.setInvoiceType(invoice.getInvoiceType());
                stockRepository.save(stock);

                // Update InvoiceProduct repository
                invoiceProduct.setEnabled(true);
                invoiceProduct.setProfit(calculateProductProfit(invoiceProduct, invoice.getInvoiceType()));
                invoiceProductRepository.save(invoiceProduct);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.toString());
        }
    }

    @Override
    public void save(InvoiceDTO invoiceDto) {
        invoiceDto.setEnabled(true);
        invoiceRepository.save(mapperUtil.convert(invoiceDto, new Invoice()));
    }

    @Override
    public Double getCompanyTotalAmount(InvoiceType invoiceType) {
        return listAllTotalInvoices(invoiceType).stream()
                .map(TotalInvoiceDto::getTotalAmount)
                .reduce(Double::sum).orElse(0.00);
    }

    @Override
    public Double getCompanyTotalTax() {
        Double totalPurchaseTax = listAllTotalInvoices(InvoiceType.PURCHASE).stream()
                .map(TotalInvoiceDto::getTotalTax)
                .reduce(Double::sum).orElse(0.00);
        Double totalSaleTax = listAllTotalInvoices(InvoiceType.SALE).stream()
                .map(TotalInvoiceDto::getTotalTax)
                .reduce(Double::sum).orElse(0.00);
        return totalPurchaseTax + totalSaleTax;
    }

    @Override
    public void update(InvoiceDTO invoiceDto) {
        invoiceRepository.save(mapperUtil.convert(invoiceDto, new Invoice()));
    }

    // todo update invoice status
    @Override
    public void delete(Long id) {
        Invoice invoice = invoiceRepository.getById(id);
        invoiceProductRepository.deleteAll(invoiceProductRepository.findAllByInvoice(invoice));
        invoiceRepository.delete(invoice);
//        invoice.setIsDeleted(true);
//        invoice.setEnabled(false);
//        invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceDTO createDraftInvoice(InvoiceType invoiceType, ClientVendorDTO clientVendorDto) {
        InvoiceDTO invoiceDto = new InvoiceDTO();
        invoiceDto.setInvoiceStatus(InvoiceStatus.DRAFT);
        invoiceDto.setInvoiceType(invoiceType);
        invoiceDto.setClientVendor(clientVendorDto);
        invoiceDto.setEnabled(false);
        invoiceDto.setInvoiceDate(LocalDate.now());
        Invoice draftInvoice = invoiceRepository.save(mapperUtil.convert(invoiceDto, new Invoice()));

        if (invoiceType.equals(InvoiceType.PURCHASE))
            draftInvoice.setInvoiceNumber("P-INV00" + draftInvoice.getId());
        else
            draftInvoice.setInvoiceNumber("S-INV00" + draftInvoice.getId());

        return mapperUtil.convert(invoiceRepository.save(draftInvoice), new InvoiceDTO());
    }

    private Double calculateTotalInvoiceAmount(Invoice invoice) {
        Optional<Double> totalInvoiceAmount = invoiceProductRepository.findAllByInvoice(invoice).stream()
                .map(invoiceProduct -> invoiceProduct.getPrice() * invoiceProduct.getQuantity())
                .reduce(Double::sum);
        return totalInvoiceAmount.isPresent() ? totalInvoiceAmount.get() : 0;

    }

    private Double calculateTotalInvoiceTax(Invoice invoice) {
        Optional<Double> totalInvoiceTax = invoiceProductRepository.findAllByInvoice(invoice).stream()
                .map(invoiceProduct -> invoiceProduct.getPrice() * invoiceProduct.getQuantity() * invoiceProduct.getTax() / 100)
                .reduce(Double::sum);
        return totalInvoiceTax.isPresent() ? totalInvoiceTax.get() : 0;
    }

    private void cleanCancelledInvoices() {
        for (Invoice invoice : invoiceRepository.findAllByEnabledIsFalse()) {
            invoiceProductRepository.deleteAll(invoiceProductRepository.findAllByInvoice(invoice));
            invoiceRepository.delete(invoice);
        }
    }

    private Double calculateProductProfit(InvoiceProduct invoiceProduct, InvoiceType invoiceType) {
        if (invoiceType.equals(InvoiceType.PURCHASE)) {
            return 0.00;
        } else {
            List<Stock> stockList = stockRepository.getPreviousPurchaseStockDataByProductId(invoiceProduct.getProduct().getId());
            int stockDataIndex = 0;
            int saleQuantity = invoiceProduct.getQuantity();
            double saleProfit = 0;

            while (saleQuantity != 0) {
                if (stockList.get(stockDataIndex).getRemainingQuantity() >= saleQuantity) {
                    saleProfit += (invoiceProduct.getPrice() - stockList.get(stockDataIndex).getPrice()) * saleQuantity;
                    stockList.get(stockDataIndex).setRemainingQuantity(stockList.get(stockDataIndex).getRemainingQuantity() - saleQuantity);
                    saleQuantity = 0;
                } else {
                    saleProfit += (invoiceProduct.getPrice() - stockList.get(stockDataIndex).getPrice()) * stockList.get(stockDataIndex).getRemainingQuantity();
                    saleQuantity = saleQuantity - stockList.get(stockDataIndex).getRemainingQuantity();
                    stockList.get(stockDataIndex).setRemainingQuantity(0);
                }
                stockRepository.save(stockList.get(stockDataIndex++));
            }
            return saleProfit;
        }
    }
}
