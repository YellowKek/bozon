package com.example.demo.controllers;

import com.example.demo.models.Favourites;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.services.FavouritesService;
import com.example.demo.services.ProductsService;
import com.example.demo.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("favourites")
public class FavouritesController {
    private final FavouritesService favouritesService;
    private final UserService userService;
    private final ProductsService productsService;

    public FavouritesController(FavouritesService favouritesService, UserService userService, ProductsService productsService) {
        this.favouritesService = favouritesService;
        this.userService = userService;
        this.productsService = productsService;
    }

    @GetMapping("")
    public String favourites(@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser, Model model) {
        User user = userService.findByUsername(authUser.getUsername()).get();
        List<Long> productsIds = favouritesService.findProductsByUser(user);
        List<Product> products = new ArrayList<>();
        for(Long prodId: productsIds) {
            products.add(productsService.findById(prodId).get());
        }
        model.addAttribute("favourite_products", products);
        return "/user/favourites";
    }
    @PatchMapping("{product_id}/add")
    public String addProduct(@PathVariable("product_id") long product, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        Optional<User> user = userService.findByUsername(authUser.getUsername());
        if (user.isPresent()) {
            Optional<Favourites> favourites = favouritesService.findByUser(user.get());
            if (favourites.isPresent()) {
                favouritesService.addProduct(favourites.get().getId(), product);
            }
        }
        return "redirect:/products/{product_id}";
    }

    @DeleteMapping("{product_id}/delete")
    public String delete(@PathVariable("product_id") long productId,
                         @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        User user = userService.findByUsername(authUser.getUsername()).get();
        Optional<Favourites> favourites = favouritesService.findByUser(user);
        if (favourites.isPresent()) {
            favouritesService.delete(favourites.get().getId(), productId);
        }
        return "redirect:/products/{product_id}";
    }

}
