package com.applicationpro.controller;

import com.applicationpro.dto.CategoryDTO;
import com.applicationpro.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String category(Model model){

        model.addAttribute("categories",categoryService.listAllCategories());


        return "category/category-list";
    }

    @GetMapping("/create")
    public String createCategory(Model model){

        model.addAttribute("category", new CategoryDTO());

        return "category/category-add";

    }

    @PostMapping("/create")
    public String insertCategory(@ModelAttribute("category") CategoryDTO categoryDTO,Model model){

        categoryService.save(categoryDTO);
//        model.addAttribute("category", new CategoryDTO());

        return "redirect:/category/list";

    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id,  Model model){

        model.addAttribute("category",categoryService.findById(id));

        return "category/category-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("category") CategoryDTO categoryDTO ){

        categoryService.edit(categoryDTO);

        return "redirect:/category/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteById(id);
        return "category/category-list";
    }






}
