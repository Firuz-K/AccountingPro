package com.applicationpro.controller;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.dto.StockDTO;
import com.applicationpro.service.ProductService;
import com.applicationpro.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class StockReportController {

    @GetMapping("/stock-report")
    public String stockReport(Model model) {
        model.addAttribute("stock",new StockDTO());
        return "report/stock-report";
    }
}
