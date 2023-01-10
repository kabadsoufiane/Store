package com.example.store.service;

import com.example.store.domain.Cart;
import com.example.store.domain.ProductQuantity;
import com.example.store.repository.CartRepository;
import com.example.store.repository.ProductQuantityRepository;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartsService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductQuantityRepository productQuantityRepository;

    public void insertCart(Cart cart, ProductQuantity productQuantity){
        cartRepository.insertData(cart);
        productQuantityRepository.insertData(productQuantity);
    }
}
