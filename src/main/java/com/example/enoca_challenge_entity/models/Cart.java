package com.example.enoca_challenge_entity.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Sepet")
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    @OneToOne
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<Product>();

    public double totalAmount() {
        double totalAmount = 0;

        for (Product product : products) {
            totalAmount = totalAmount + (product.getQuantity() * product.getPrice());
        }

        return totalAmount;
    }




}
