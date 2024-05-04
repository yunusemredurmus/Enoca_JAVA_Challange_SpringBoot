package com.example.enoca_challenge_entity.controller.customer;

import com.example.enoca_challenge_entity.models.Customer;
import com.example.enoca_challenge_entity.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            Customer newCustomer = customerRepo.save(customer);
            System.out.println(newCustomer);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);


        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}