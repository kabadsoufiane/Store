package com.example.store.service;

import com.example.store.domain.Product;
import com.example.store.domain.Rating;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RatingsRepository ratingsRepository;
    public void insertProduct(Product product){
        productRepository.insertData(product);
        if(product.getRating() != null) {
            ratingsRepository.insertData((Rating) product.getRating());
        }
    }
}
