package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repos.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartService {
    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Transactional
    public void save(Cart cart) {
        cartRepo.save(cart);
    }

    public Cart findByUser(User user) {
        return cartRepo.findByUser(user);
    }

    @Transactional
    public void addProduct(User user, Product product) {
        Cart cart = cartRepo.findByUser(user);
//        List<Product> products = cart.getProducts();
//        products.add(product);
        cartRepo.add(product.getId(), cart.getId());
    }
}
