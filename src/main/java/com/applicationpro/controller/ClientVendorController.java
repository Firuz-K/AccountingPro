package com.applicationpro.controller;

import com.applicationpro.dto.ClientVendorDto;
import com.applicationpro.enums.State;
import com.applicationpro.service.ClientVendorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clientvendor")
public class ClientVendorController {

    private final Logger logger = LogManager.getLogger(ClientVendorController.class);

    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @GetMapping("/client-vendor-list")
    public String listClientVendors(Model model) {
        List<ClientVendorDto> clientVendorList = clientVendorService.listAllClientVendors();
        logger.info("Client Vendor list: " + clientVendorList);
        model.addAttribute("clientVendorList", clientVendorList);
        return "/clientvendor/client-vendor-list";
    }

    @GetMapping("/client-vendor-add")
    public String addClientVendor(Model model) {
        model.addAttribute("clientVendor", new ClientVendorDto());
        model.addAttribute("stateList", List.of(State.values()));
        return "/clientvendor/client-vendor-add";
    }

    @PostMapping("/client-vendor-add")
    public String addClientVendor(@ModelAttribute("clientVendor") ClientVendorDto clientVendorDto) {
        logger.info("Client Vendor to be added: " + clientVendorDto);
        clientVendorService.save(clientVendorDto);
        return "redirect:/clientvendor/client-vendor-list";
    }

    @GetMapping("/client-vendor-edit/{clientVendorId}")
    public String editClientVendor(@PathVariable("clientVendorId") Long clientVendorId, Model model) {
        logger.info("Client Vendor to be edited: " + clientVendorService.findClientVendorById(clientVendorId));

        model.addAttribute("clientVendor", clientVendorService.findClientVendorById(clientVendorId));
        model.addAttribute("stateList", List.of(State.values()));
        return "/clientvendor/client-vendor-edit";
    }

    @PostMapping("/client-vendor-edit")
    public String editClientVendor(ClientVendorDto clientVendorDto) {
        logger.info("Client Vendor edited = " + clientVendorDto);
        clientVendorService.update(clientVendorDto);
        return "redirect:/clientvendor/client-vendor-list";
    }

    @GetMapping("/delete/{clientVendorId}")
    public String deleteClientVendor(@PathVariable("clientVendorId") Long clientVendorId) {
        logger.info("Client Vendor to be deleted: " + clientVendorId);
        clientVendorService.delete(clientVendorId);
        return "redirect:/clientvendor/client-vendor-list";
    }
}
