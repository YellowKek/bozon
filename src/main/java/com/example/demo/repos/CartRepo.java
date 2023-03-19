package com.example.demo.repos;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
    @Modifying
    @Query(value = "insert into products_carts (product_id, cart_id) values (:product, :cart);", nativeQuery = true)
    void add(@Param("product") long product, @Param("cart") long cart);

    @Modifying
    @Query(value = "delete from products_carts where product_id=:productId and cart_id=:cartId", nativeQuery = true)
    void deleteProductById(@Param("productId") long productId, @Param("cartId") long cartId);
}
