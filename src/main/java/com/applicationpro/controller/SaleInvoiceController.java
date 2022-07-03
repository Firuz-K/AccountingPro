package com.applicationpro.controller;

import com.applicationpro.dto.ClientVendorDTO;
import com.applicationpro.dto.InvoiceDTO;
import com.applicationpro.dto.InvoiceProductDTO;
import com.applicationpro.enums.InvoiceType;
import com.applicationpro.service.ClientVendorService;
import com.applicationpro.service.InvoiceProductService;
import com.applicationpro.service.InvoiceService;
import com.applicationpro.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class SaleInvoiceController {
    private final Logger logger = LogManager.getLogger(PurchaseInvoiceController.class);
    private final InvoiceService invoiceService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;


    public SaleInvoiceController(InvoiceService invoiceService, ClientVendorService clientVendorService, ProductService productService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping("/sale-invoice-list")
    public String listSaleInvoices(Model model){
        model.addAttribute("invoiceList", invoiceService.listAllTotalInvoices(InvoiceType.SALE));
        model.addAttribute("clientVendorList", clientVendorService.listAllActiveClients());
        model.addAttribute("clientVendor", new ClientVendorDTO());

        return "invoice/sales-invoice-list";

    }

    @GetMapping("/sales-invoice-create")
    public String createPurchaseInvoice(@ModelAttribute("clientVendorId") ClientVendorDTO clientVendor, Model model) {
        logger.info("Purchase info request for the vendor company Id: " + clientVendor.getId());

        InvoiceDTO draftInvoice = invoiceService.createDraftInvoice(InvoiceType.SALE,
                clientVendorService.findClientVendorById(clientVendor.getId()));

        logger.info("Created draft invoice: " + draftInvoice);
        model.addAttribute("invoice", draftInvoice);
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("productList", productService.listAllActiveProducts());
        return "/invoice/sales-invoice-create";
    }

    @PostMapping("/sale-invoice-create")
    public String createPurchaseInvoice(@ModelAttribute("invoice") InvoiceDTO invoice) {
        invoice = invoiceService.findInvoiceById(invoice.getId());
        logger.info("Invoice to be created: " + invoice);
        invoiceService.save(invoice);
        return "redirect:/invoice/sales-invoice-list";
    }

    @GetMapping("/sales-invoice-select-product/{invoiceId}")
    public String purchaseInvoiceSelectProduct(@PathVariable("invoiceId") Long invoiceId,
                                               Model model) {
        model.addAttribute("productList", productService.listAllActiveProducts());
        model.addAttribute("invoice", invoiceService.findInvoiceById(invoiceId));
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("invoiceProductList", invoiceProductService.listInvoiceProductsByInvoiceId(invoiceId));
        return "/invoice/sales-invoice-select-product";
    }
    @PostMapping("/sales-invoice-select-product/{invoiceId}")
    public String purchaseInvoiceSelectProduct(@ModelAttribute("invoiceProduct") InvoiceProductDTO invoiceProductDto,
                                               @PathVariable("invoiceId") Long invoiceId,
                                               Model model) {
        logger.info("invoice product to be added invoiceId:"+invoiceId +" invoiceProductDto " + invoiceProductDto);
        invoiceProductDto.setName(invoiceProductDto.getProduct().getName());
        invoiceProductDto.setInvoice(invoiceService.findInvoiceById(invoiceId));
        invoiceProductService.save(invoiceProductDto);

        logger.info("invoice product to be added: " + invoiceProductDto);
        model.addAttribute("productList", productService.listAllActiveProducts());
        model.addAttribute("invoice", invoiceService.findInvoiceById(invoiceProductDto.getInvoice().getId()));
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("invoiceProductList", invoiceProductService.listInvoiceProductsByInvoiceId(invoiceId));
        return "redirect:/invoice/sales-invoice-select-product/"+ invoiceId;
    }
}
