package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "products_carts")
public class ProductsCarts {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;
    @OneToOne
    private Cart cart;
    
}
