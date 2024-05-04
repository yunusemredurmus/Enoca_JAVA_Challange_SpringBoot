package com.example.enoca_challenge_entity.controller.order;

import com.example.enoca_challenge_entity.models.Customer;
import com.example.enoca_challenge_entity.models.Order;
import com.example.enoca_challenge_entity.repo.CustomerRepo;
import com.example.enoca_challenge_entity.repo.OrderRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderRepo orderRepo;
    CustomerRepo customerRepo;

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        try {
            Optional<Order> order = orderRepo.findById(id);
            if (order.isPresent()) {
                return ResponseEntity.ok(order.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/order/placeorder")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        try {
            Order savedOrder = orderRepo.save(order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        try {
            Optional<Customer> customer = customerRepo.findById(customerId);
            if (customer.isPresent()) {
                Customer newcustomer = customer.get();
                List<Order> orders = orderRepo.findAll();
                return new ResponseEntity<>(orders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








}