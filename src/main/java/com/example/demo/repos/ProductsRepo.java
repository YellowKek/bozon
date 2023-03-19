package com.example.demo.repos;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(@NotNull(message = "must be not null") Category category);
}
