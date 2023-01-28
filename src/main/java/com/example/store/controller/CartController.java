package com.example.store.controller;

import com.example.store.domain.Cart;
import com.example.store.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartsService cartsService;

    @PostMapping("/addCart")
    public ResponseEntity<Cart> addProduct(@RequestBody Cart request) {
        Cart cart = Cart.builder()
                .id_cart(request.getId_cart())
                .id_user(request.getId_user())
                .date(request.getDate())
                .build();
        try {
            cartsService.insertCart(cart);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getCarts(){
        List<Cart> carts = cartsService.getAllCart();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/carts/{id_cart}")
    public ResponseEntity<List<Cart>> getCartById(@PathVariable int id_cart){
        List<Cart> carts = cartsService.getCartById(id_cart);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/carts/{date}")
    public ResponseEntity<List<Cart>> getCartById(@PathVariable LocalDate date){
        List<Cart> carts = cartsService.getCarteByDate(date);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/carts/{startdate}/{enddate}")
    public ResponseEntity<List<Cart>> getCartsByDatesRange(@PathVariable("startdate") String startDate, @PathVariable("enddate") String endDate){
        LocalDate date1 = LocalDate.parse(startDate);
        LocalDate date2 = LocalDate.parse(endDate);
        List<Cart> carts = cartsService.getCarteByDatesRange(date1, date2);
        if(carts.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(carts, HttpStatus.OK);
        }
    }

    @GetMapping("/carts/{id_user}")
    public ResponseEntity<List<Cart>> getCartByUser(@PathVariable int id_user){
        List<Cart> carts = cartsService.getCartByUser(id_user);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PutMapping("/updateCart/{id_cart}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id_cart, @RequestBody Cart request) {
        try {
            cartsService.updateCartById(id_cart, request.getId_user(), request.getDate());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_cart}")
    public ResponseEntity<Void> deleteCartById(@PathVariable int id_cart) {
        cartsService.deleteCartById(id_cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
