package com.example.store.domain;

import lombok.*;

import java.util.List;

@Builder
@Data
public class Product {

    @NonNull
    private int id_product;
    @NonNull
    private String title;
    @NonNull
    private double price;
    @NonNull
    private String category;
    @NonNull
    private String description;
    @NonNull
    private String image;
    private List<Rating> rating;

}
