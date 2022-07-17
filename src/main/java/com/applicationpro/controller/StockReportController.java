package com.applicationpro.controller;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.dto.StockDTO;
import com.applicationpro.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/report")
public class StockReportController {

    private final ProductService productService;

    public StockReportController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/stock-report")
    public String stockReport(Model model) {

        List<ProductDTO> productStocks = productService.listProductStockDetails();

        model.addAttribute("productStock",productStocks);

        return "report/stock-report";
    }
}
