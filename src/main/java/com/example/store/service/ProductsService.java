package com.example.store.service;

import com.example.store.domain.Product;
import com.example.store.domain.Rating;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.RatingsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> getAllProducts(){
        return productRepository.getAllProduct();
    }

    public List<Product> getProductById(@NonNull int id_product){
        return productRepository.getProductById(id_product);
    }

    public List<Product> getAllCategories(){
        return productRepository.getAllCategories();
    }

    public List<Product> getProductsByCategory(@NonNull String category){
        return productRepository.getProductByCategory(category);
    }

    public void updateProductById(@NonNull int id_product, String title, double price, String category,
                                  String description, String image){
        productRepository.updateProductById(id_product, title, price, category, description, image);
    }

    public void deleteProductById(@NonNull int id_product){
        productRepository.deleteProductsById(id_product);
    }
}
