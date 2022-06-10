package com.applicationpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {


    @GetMapping("/category-list")
    public String categoryList(){
        return "category/category-list";
    }

}
