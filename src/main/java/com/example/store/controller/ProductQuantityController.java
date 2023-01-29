package com.example.store.controller;

import com.example.store.domain.ProductQuantity;
import com.example.store.service.ProductQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductQuantityController {

    @Autowired
    ProductQuantityService productQuantityService;

    @PostMapping("/addProductQuantity")
    public ResponseEntity<ProductQuantity> addProductQuantity(@RequestBody ProductQuantity request){
        try {
            productQuantityService.insertProductQuantities(request);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping("/allProductsQuantities")
    public ResponseEntity<List<ProductQuantity>> getProductQuantities(){
        List<ProductQuantity> productQuantities = productQuantityService.getProductQuantities();
        return new ResponseEntity<>(productQuantities, HttpStatus.OK);
    }

    @GetMapping("/productsQuantitiesById/{id_product}")
    public ResponseEntity<List<ProductQuantity>> getProductQuantitiesById(@PathVariable int id_product){
        List<ProductQuantity> productQuantities = productQuantityService.getProductQuantitiesById(id_product);
        return new ResponseEntity<>(productQuantities, HttpStatus.OK);
    }

    @PutMapping("/updateProductQuantity/{id_product}")
    public ResponseEntity<ProductQuantity> updateProductQuantitiesById(@PathVariable int id_product,
            @RequestBody ProductQuantity request){
        try {
            productQuantityService.updateProductQuantitiesById(id_product, request.getQuantity());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_product}")
    public ResponseEntity<Void> deleteProductQuantitiesById(@PathVariable int id_product){
        productQuantityService.deleteProductQuantitiesById(id_product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
