package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repos.CartRepo;
import com.example.demo.repos.ProductsRepo;
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
    private final ProductsRepo productsRepo;

    @Autowired
    public CartService(CartRepo cartRepo,
                       ProductsRepo productsRepo) {
        this.cartRepo = cartRepo;
        this.productsRepo = productsRepo;
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
        cartRepo.add(product.getId(), cart.getId());
    }

    @Transactional
    public void deleteProductById(long productId, long cartId) {
        cartRepo.deleteProductById(productId, cartId);
    }
    public int sum(Cart cart) {
        int sum = 0;
        for (Product product: cart.getProducts()) {
            sum += product.getPrice();
        }
        return sum;
    }
}
