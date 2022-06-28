package com.applicationpro.controller;

import com.applicationpro.dto.CompanyDTO;
import com.applicationpro.dto.RoleDTO;
import com.applicationpro.dto.UserDTO;
import com.applicationpro.enums.UserStatus;
import com.applicationpro.service.CompanyService;
import com.applicationpro.service.RoleService;
import com.applicationpro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/user-update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.findByID(id));
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAll());
        model.addAttribute("companies", companyService.listAllCompanies());
        model.addAttribute("userStatus", UserStatus.values());

        return "/user/user-update";

    }

    @PostMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id,@ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

//        if (bindingResult.hasErrors()) {
//
//            model.addAttribute("roles", roleService.listAllRoles());
//            model.addAttribute("users", userService.listAll());
//            model.addAttribute("companies", companyService.listAllCompanies());
//
//
//            return "/user/user-update";
//
//        }

        userService.update(user);
        return "redirect:/user/user-list";

    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.delete(username);
        return "redirect:/user/create";
    }

}
