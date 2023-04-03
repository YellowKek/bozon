package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "favourites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favourites {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    @ManyToMany(mappedBy = "favourites")
    private List<Product> products;
    public Favourites(User user) {
        this.user = user;
    }
}
