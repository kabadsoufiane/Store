package com.example.store.controller;

import com.example.store.domain.Product;
import com.example.store.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductsService productsService;

    @PostMapping(value = "/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product request) {
        try {
            productsService.insertProduct(request);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping("/GetAllProducts")
    public ResponseEntity<List<Product>> GetAllProducts(){
        List<Product> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/GetProductById/{id_product}")
    public ResponseEntity<List<Product>> GetProductById(@PathVariable int id_product){
        List<Product> products = productsService.getProductById(id_product);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Product>> getAllCategories(){
        List<Product> products = productsService.getAllCategories();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/getProductsByCategory/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category){
        List<Product> products = productsService.getProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id_product}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id_product, @RequestBody Product request) {
        try {
            productsService.updateProductById(id_product, request.getTitle(), request.getPrice(),
                    request.getCategory(), request.getDescription(), request.getImage());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        productsService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
