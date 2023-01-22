package com.example.store.service;

import com.example.store.domain.Cart;
import com.example.store.domain.ProductQuantity;
import com.example.store.repository.CartRepository;
import com.example.store.repository.ProductQuantityRepository;
import com.example.store.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CartsService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductQuantityRepository productQuantityRepository;

    public void insertCart(Cart cart, ProductQuantity productQuantity){
        cartRepository.insertData(cart);
        productQuantityRepository.insertData(productQuantity);
    }

    public List<Cart> getAllCart(){
        return cartRepository.getAllCart();
    }

    public List<Cart> getCartById(@NonNull int id_cart){
        return cartRepository.getCartById(id_cart);
    }

    public List<Cart> getCarteByDatesRange(@NonNull LocalDate date){
        return cartRepository.getCartByDate(date);
    }

    public List<Cart> getCarteByDatesRange(@NonNull LocalDate date1, @NonNull LocalDate date2){
        return cartRepository.getCartByDateRange(date1, date2);
    }

    public List<Cart> getCartByUser(@NonNull int id_user){
        return cartRepository.getCartByUser(id_user);
    }

    public void updateCartById(@NonNull int id_cart, int id_user, LocalDate date){
        cartRepository.setUpdateCartById(id_cart, id_user, date);
    }

    public void deleteCartById(@NonNull int id_cart){
        cartRepository.deleteCartById(id_cart);
    }
}
