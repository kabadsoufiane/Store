package com.example.store;

import com.example.store.controller.ProductController;
import com.example.store.domain.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    ProductController productController;
    @Test
    void contextLoads() {
    }
    @Test
    public void insertProductTest(){
        productController.addProduct("test product", 13.5, "electronic", "lorem ipsum set",
                "https://i.pravatar.cc", null);
    }


}
