package com.example.store.service;

import com.example.store.domain.ProductQuantity;
import com.example.store.repository.ProductQuantityRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQuantityService {
    @Autowired
    ProductQuantityRepository productQuantityRepository;

    public void insertProductQuantities(ProductQuantity productQuantity){
        productQuantityRepository.insertData(productQuantity);
    }

    public List<ProductQuantity> getProductQuantities(){
        return productQuantityRepository.getProductQuantities();
    }

    public List<ProductQuantity> getProductQuantitiesById(@NonNull int id_product){
        return productQuantityRepository.getProductQuantitiesById(id_product);
    }

    public void updateProductQuantitiesById(@NonNull int id_product, int productquantity){
        productQuantityRepository.updateProductQuantitiesById(id_product, productquantity);
    }

    public void deleteProductQuantitiesById(@NonNull int id_product){
        productQuantityRepository.deleteProductQuantitiesById(id_product);
    }
}
