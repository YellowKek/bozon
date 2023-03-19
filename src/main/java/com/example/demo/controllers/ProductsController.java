package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("")
    public String catalog(Model model, @RequestParam(name = "category", required = false, defaultValue = "all") String category) {
        if (!category.equals("all")) {
            model.addAttribute("products", productsService.findByCategory(Category.valueOf(category)));
        }
        else {
            model.addAttribute("products", productsService.findAll());
        }
        model.addAttribute("categories", Category.values());
        return "products/products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") long id, Model model) {
        if (productsService.findById(id).isPresent()) {
            model.addAttribute("product", productsService.findById(id).get());
            return "products/product";
        }
        return "products/products";

    }
    // TODO
    // фильтрация по категории
    // корзина
}
