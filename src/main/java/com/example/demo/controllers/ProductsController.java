package com.example.demo.controllers;

import com.example.demo.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("")
    public String catalog(Model model) {
        model.addAttribute("products", productsService.findAll());
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
