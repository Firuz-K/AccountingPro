package com.applicationpro.controller;

import com.applicationpro.dto.StockDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportsController {

    @GetMapping("/stock-report")
    public String stockReport(Model model) {
        model.addAttribute("stock",new StockDTO());


            return "report/stock-report";
        }

    @GetMapping("/profit-loss-report")
    public String profitLossReport() {
        return "report/profit-loss-report";
    }



}
