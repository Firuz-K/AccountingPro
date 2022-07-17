package com.applicationpro.controller;

import com.applicationpro.dto.ProductDTO;
import com.applicationpro.enums.ProductStatus;
import com.applicationpro.enums.Unit;
import com.applicationpro.service.CategoryService;
import com.applicationpro.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    // ask about this list and creation
    @GetMapping("/list")
    public String getListOfProduct(Model model) {

        List<ProductDTO> productDTOList = productService.listAllProducts();
        model.addAttribute("productDTOList", productDTOList);

        return "/product/product-list";

    }

    // creating new product
    // is create and save on edit page are same?
    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("units", Unit.values());
        model.addAttribute("status", ProductStatus.values());

        return "/product/product-add";

    }


    @PostMapping("/create")
    public String addProduct(@ModelAttribute("product") ProductDTO product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", new ProductDTO());
            model.addAttribute("categories", categoryService.listAllCategories());
            model.addAttribute("units", Unit.values());
            model.addAttribute("status", ProductStatus.values());

            return "/product/product-add";

        }
        productService.create(product);
        return "redirect:/product/product-list";
    }
}