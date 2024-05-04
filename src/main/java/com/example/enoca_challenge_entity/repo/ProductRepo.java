package com.example.enoca_challenge_entity.repo;

import com.example.enoca_challenge_entity.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepo extends JpaRepository<Product,Long> {
}
