package com.example.enoca_challenge_entity.controller.cart;

import com.example.enoca_challenge_entity.models.Cart;
import com.example.enoca_challenge_entity.models.Product;
import com.example.enoca_challenge_entity.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController  {
    @Autowired
    CartRepo cartRepo;

    @DeleteMapping("/deleteAllCart")
    public ResponseEntity<HttpStatus> deleteAllCart() {
        try {
            cartRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateCart/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        try {
            Optional<Cart> cartData = cartRepo.findById(id);
            if (cartData.isPresent()) {
                Cart updatedCartData = cartData.get();
               updatedCartData.setCustomer(cart.getCustomer());
               updatedCartData.setProducts(cart.getProducts());


                Cart cartObj = cartRepo.save(updatedCartData);
                return new ResponseEntity<>(cartObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Optional<Cart> cart = cartRepo.findById(id);
        return cart.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addProduct")
    public void addProductToCart( @RequestBody Cart cart, @RequestBody Product product) {
        Optional<Cart> cartObj = cartRepo.findById(cart.getId());
        cartObj.ifPresent(value -> {
            Optional<Product> existingProductOptional = value.getProducts().stream()
                    .filter(p -> Objects.equals(p.getId(), product.getId()))
                    .findFirst();

            if (existingProductOptional.isPresent()) {
                Product existingProduct = existingProductOptional.get();
                existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            } else {
                value.getProducts().add(product);
            }
        });
    }

    @PostMapping("/removeProduct")
    public void removeProductFromCart( @RequestBody Cart cart, @RequestBody Product product) {
        Optional<Cart> cartObj = cartRepo.findById(cart.getId());
        cartObj.ifPresent(value -> {
            value.getProducts().stream().filter(p -> Objects.equals(p.getId(), product.getId())).findFirst().ifPresent(
                    p -> {
                        if(p.getQuantity() == product.getQuantity()){
                            value.getProducts().remove(p);
                        }else{
                            p.setQuantity(p.getQuantity() - product.getQuantity());
                        }
                    }
            );
        });
    }
}
