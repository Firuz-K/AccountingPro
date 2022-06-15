package com.applicationpro.controller;


import com.applicationpro.dto.CompanyDTO;
import com.applicationpro.enums.State;
import com.applicationpro.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final Logger logger = LogManager.getLogger(CompanyController.class);
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company-list")
    public String listCompanies(Model model) {
        List<CompanyDTO> companyList = companyService.listAllCompanies();
        logger.info("Company list: " + companyList);

        model.addAttribute("companyList", companyList);
        return "/company/company-list";
    }

    @GetMapping("/company-add")
    public String addCompany(Model model) {

        model.addAttribute("company", new CompanyDTO());
        model.addAttribute("stateList", List.of(State.values()));

        return "/company/company-add";
    }

    @PostMapping("/company-add")
    public String addCompany(@ModelAttribute("company") CompanyDTO companyDTO) {
        logger.info("Company to be added: " + companyDTO);

        companyService.save(companyDTO);

        return "redirect:/company/company-list";
    }

    @GetMapping("/company-edit/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model){

        logger.info("Company to be edited: " + companyService.findCompanyById(id));

        model.addAttribute("company", companyService.findCompanyById(id));
        model.addAttribute("stateList", List.of(State.values()));

        return "/company/company-edit";
    }

    @PostMapping("/company-edit")
    public String editCompany(CompanyDTO companyDTO){
        logger.info("Company edited: " + companyDTO);
        companyService.update(companyDTO);

        return "redirect:/company/company-list";
    }

    @GetMapping("/close/{id}")
    public String closeCompany(@PathVariable("id") Long id){

        logger.info("Company to be deleted: " + companyService.findCompanyById(id));
        companyService.disable(id);

        return "redirect:/company/company-list";
    }

    @GetMapping("/re-open/{id}")
    public String reOpenCompany(@PathVariable("id") Long id){

        logger.info("Company to be deleted: " + companyService.findCompanyById(id));
        companyService.enable(id);

        return "redirect:/company/company-list";
    }


}
