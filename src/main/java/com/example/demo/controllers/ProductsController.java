package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repos.CartRepo;
import com.example.demo.services.CartService;
import com.example.demo.services.ProductsService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    private final UserService userService;
    private final CartService cartService;
    private final CartRepo cartRepo;

    @Autowired
    public ProductsController(ProductsService productsService, UserService userService, CartService cartService,
                              CartRepo cartRepo) {
        this.productsService = productsService;
        this.userService = userService;
        this.cartService = cartService;
        this.cartRepo = cartRepo;
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
    public String getProduct(@PathVariable("id") long id, Model model,
                             @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        if (productsService.findById(id).isPresent()) {
            model.addAttribute("product", productsService.findById(id).get());
            boolean flag = false;
            if (authUser != null) {
                User user = userService.findByUsername(authUser.getUsername()).get();
                model.addAttribute("cart", cartService.findByUser(user));
                flag = true;
            }
            model.addAttribute("flag", flag);
            return "products/product";
        }
        return "products/products";
    }

    @PatchMapping("/{id}/add_to_cart")
    public String addToCart(@PathVariable("id") long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser,
                            Model model) {
        User user = userService.findByUsername(authUser.getUsername()).get();
        Product product = productsService.findById(id).get();
        cartService.addProduct(user, product);
        model.addAttribute("cart", cartService.findByUser(user));
        return "redirect:/products/{id}";
    }

    @DeleteMapping("{id}/delete_from_cart")
    public String deleteFromCart(@PathVariable("id") long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser,
                                 Model model){
        User user = userService.findByUsername(authUser.getUsername()).get();
        cartService.deleteProductById(id, cartRepo.findByUser(user).getId());
        return "redirect:/user/cart";
    }
}
