package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull(message = "must be not null")
    @Size(min = 2, message = "min size is 2")
    private String name;
    @Column(name = "category")
    @NotNull(message = "must be not null")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(name = "price")
    @NotNull(message = "must be not null")
    @Min(value = 10, message = "min price is 10")
    private int price;
    @Column(name = "description")
    @Size(min = 10, message = "min size of description is 10 chars")
    @NotNull(message = "must be not null")
    private String description;

//    @Column(name = "seller")
//    @NotNull(message = "must be not null")
//    private String seller;
//    private List<Cart> carts;
//    @JoinTable(
//            name = ""
//    )
//    private List<Favourites> favourites;
}
