package com.applicationpro.controller;

import com.applicationpro.dto.CompanyDTO;
import com.applicationpro.dto.RoleDTO;
import com.applicationpro.dto.UserDTO;
import com.applicationpro.service.CompanyService;
import com.applicationpro.service.RoleService;
import com.applicationpro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;
    private final RoleService roleService;

    public UserController(UserService userService, CompanyService companyService, RoleService roleService) {
        this.userService = userService;
        this.companyService = companyService;
        this.roleService = roleService;
    }

    @GetMapping ("/user-list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAll());
        return "/user/user-list";
    }

    @GetMapping("/user-add")
    public String addUser(Model model){
        model.addAttribute("user",new UserDTO());
        List<CompanyDTO> companyList = companyService.listAllCompanies();
        model.addAttribute("companyList", companyList);
        List<RoleDTO> roleDTOList = roleService.listAllRoles();
        model.addAttribute("roleList", roleDTOList);

        return "/user/user-add";
    }

    @PostMapping("/user-add")
    public String addUser(@ModelAttribute("user") UserDTO userDTO){


        userService.save(userDTO);


        return "redirect:/user/user-list";
    }
}
