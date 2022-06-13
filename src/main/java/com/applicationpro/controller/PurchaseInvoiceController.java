package com.applicationpro.controller;


import com.applicationpro.dto.ClientVendorDTO;
import com.applicationpro.dto.InvoiceDTO;
import com.applicationpro.dto.InvoiceProductDTO;
import com.applicationpro.enums.InvoiceType;
import com.applicationpro.service.ClientVendorService;
import com.applicationpro.service.InvoiceProductService;
import com.applicationpro.service.InvoiceService;
import com.applicationpro.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
@Log4j2
public class PurchaseInvoiceController {

    private final Logger logger = LogManager.getLogger(PurchaseInvoiceController.class);

    private final InvoiceService invoiceService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;

    public PurchaseInvoiceController(InvoiceService invoiceService,
                                     ClientVendorService clientVendorService,
                                     ProductService productService,
                                     InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping("/purchase-invoice-list")
    public String listPurchaseInvoices(Model model) {
        model.addAttribute("invoiceList", invoiceService.listAllTotalInvoices(InvoiceType.PURCHASE));
        model.addAttribute("clientVendorList", clientVendorService.listAllActiveVendors());
        model.addAttribute("clientVendor", new ClientVendorDTO());
        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/purchase-invoice-approve/{invoiceId}")
    public String approvePurchaseInvoice(@PathVariable("invoiceId") Long invoiceId,Model model) {
        logger.info("Purchase Invoice to be approved: "+ invoiceId);
        try {
            invoiceService.approve(invoiceId);
        }catch (Exception e){
            logger.error("invoice #: "+invoiceId +" is not getting approved. There is a stock error, check your inventory");
            logger.error(e.getMessage());
            return "redirect:/invoice/purchase-invoice-list";
        }
        model.addAttribute("invoiceList", invoiceService.listAllTotalInvoices(InvoiceType.PURCHASE));
        model.addAttribute("clientVendorList", clientVendorService.listAllActiveVendors());
        model.addAttribute("clientVendor", new ClientVendorDTO());
        return "redirect:/invoice/purchase-invoice-list";
    }

    @GetMapping("/purchase-invoice-edit/{invoiceId}")
    public String editPurchaseInvoice(@PathVariable("invoiceId") Long invoiceId,
                                      Model model) {
        model.addAttribute("productList", productService.listAllActiveProducts());
        model.addAttribute("invoice", invoiceService.findInvoiceById(invoiceId));
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("invoiceProductList", invoiceProductService.listInvoiceProductsByInvoiceId(invoiceId));
        return "/invoice/purchase-invoice-select-product";
    }

    @GetMapping("/purchase-invoice-delete/{invoiceId}")
    public String deletePurchaseInvoice(@PathVariable("invoiceId") Long invoiceId,
                                        Model model) {
        logger.info("Purchase Invoice to be deleted: "+ invoiceId);
        invoiceService.delete(invoiceId);
        model.addAttribute("invoiceList", invoiceService.listAllTotalInvoices(InvoiceType.PURCHASE));
        model.addAttribute("clientVendorList", clientVendorService.listAllActiveVendors());
        model.addAttribute("clientVendor", new ClientVendorDTO());
        return "redirect:/invoice/purchase-invoice-list";
    }

    @GetMapping("/purchase-invoice-toinvoice/{invoiceId}")
    public String toInvoicePurchaseInvoice(@PathVariable("invoiceId") Long invoiceId,Model model) {
        logger.info("Purchase Invoice to be printed: "+ invoiceService.findInvoiceById(invoiceId));
        model.addAttribute("invoice",invoiceService.findInvoiceById(invoiceId));
        model.addAttribute("invoiceProductList", invoiceProductService.listInvoiceProductsByInvoiceId(invoiceId));
        model.addAttribute("totalInvoice",invoiceService.findTotalInvoiceById(invoiceId));
        return "/invoice/toInvoice";
    }

    @GetMapping("/purchase-invoice-create")
    public String createPurchaseInvoice(@ModelAttribute("clientVendorId") ClientVendorDTO clientVendor, Model model) {
        logger.info("Purchase info request for the vendor company Id: " + clientVendor.getId());

        InvoiceDTO draftInvoice = invoiceService.createDraftInvoice(InvoiceType.PURCHASE,
                clientVendorService.findClientVendorById(clientVendor.getId()));

        logger.info("Created draft invoice: " + draftInvoice);
        model.addAttribute("invoice", draftInvoice);
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("productList", productService.listAllActiveProducts());
        return "/invoice/purchase-invoice-create";
    }

    @PostMapping("/purchase-invoice-create")
    public String createPurchaseInvoice(@ModelAttribute("invoice") InvoiceDTO invoice) {
        invoice = invoiceService.findInvoiceById(invoice.getId());
        logger.info("Invoice to be created: " + invoice);
        invoiceService.save(invoice);
        return "redirect:/invoice/purchase-invoice-list";
    }


    @GetMapping("/purchase-invoice-select-product/{invoiceId}")
    public String purchaseInvoiceSelectProduct(@PathVariable("invoiceId") Long invoiceId,
                                               Model model) {
        model.addAttribute("productList", productService.listAllActiveProducts());
        model.addAttribute("invoice", invoiceService.findInvoiceById(invoiceId));
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("invoiceProductList", invoiceProductService.listInvoiceProductsByInvoiceId(invoiceId));
        return "/invoice/purchase-invoice-select-product";
    }

    @PostMapping("/purchase-invoice-select-product/{invoiceId}")
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
        return "redirect:/invoice/purchase-invoice-select-product/"+ invoiceId;
    }

    @PostMapping("/purchase-invoice-update-products/{productId}")
    public String purchaseInvoiceUpdateProducts(@PathVariable("productId") Long productId,
                                                @ModelAttribute("invoiceId") Long invoiceId,
                                                Model model) {
        logger.info("Invoice product id to be deleted: " + productId);
        invoiceProductService.delete(productId);
        model.addAttribute("productList", productService.listAllActiveProducts());
        model.addAttribute("invoice", invoiceService.findInvoiceById(invoiceId));
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("invoiceProductList", invoiceProductService.listInvoiceProductsByInvoiceId(invoiceId));
        return "redirect:/invoice/purchase-invoice-select-product/" + invoiceId;
    }
}
