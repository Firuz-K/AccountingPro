package com.applicationpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ProfitLossReportController {

    @GetMapping("/profit-loss-report")
    public String profitLossReport() {
        return "report/profit-loss-report";
    }

}
