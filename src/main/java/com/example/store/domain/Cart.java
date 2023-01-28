package com.example.store.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Cart {

    @NonNull
    private int id_cart;
    @NonNull
    private User id_user;
    @NonNull
    private LocalDate date;
    private List<ProductQuantity> productQuantities;
}
