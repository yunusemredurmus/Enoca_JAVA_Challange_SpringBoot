package com.example.enoca_challenge_entity.repo;

import com.example.enoca_challenge_entity.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CustomerRepo extends JpaRepository<Customer,Long> {
}
