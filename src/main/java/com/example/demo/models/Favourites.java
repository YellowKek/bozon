package com.example.demo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "favourites")
public class Favourites {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    @ManyToMany(mappedBy = "favourites")
    private List<Product> products;
}
