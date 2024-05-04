package com.example.enoca_challenge_entity.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product")
public class Product extends BaseEntity {

    private String name;
    private double price;
    private int stock;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart")
    private Cart cart;

}
