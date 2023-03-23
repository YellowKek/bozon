package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToOne
    private User user;
    @OneToOne
    private Product product;
    @Column(name = "quantity")
    @Min(value = 0, message = "min count is 1")
    private int quantity;

    public Cart(User user) {
        this.user = user;
    }

    public Cart(User user, Product product) {
        this.user = user;
        this.product = product;
        this.quantity = 1;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
