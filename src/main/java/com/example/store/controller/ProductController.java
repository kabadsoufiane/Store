package com.example.store.controller;

import com.example.store.domain.Product;
import com.example.store.domain.Rating;
import com.example.store.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ProductController {
    @Autowired
    ProductsService productsService;

    @PostMapping(value = "/addProduct")
    public ResponseEntity<Product> addProduct(String title, double price, String category, String
                           description, String image, List<Rating> rating){
        Product product = Product.builder()
                .title(title)
                .price(price)
                .category(category)
                .description(description)
                .image(image)
                .rating(rating)
                .build();
        productsService.insertProduct(product);
        if(product == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }

    }

}
