//package com.example.demo.models;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Min;
//
//@Entity
//@Table(name = "products_carts")
//public class ProductsCarts {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @OneToOne
//    private Product product;
//    @OneToOne
//    private User user;
//    @Column(name = "product_count")
//    @Min(value = 0, message = "min count is 1")
//    private int count;
//}
