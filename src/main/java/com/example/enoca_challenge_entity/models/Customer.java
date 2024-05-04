package com.example.enoca_challenge_entity.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Müşteriler")
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {


    private String name;

    @OneToOne(mappedBy = "customer")
    private Cart cart;


}
