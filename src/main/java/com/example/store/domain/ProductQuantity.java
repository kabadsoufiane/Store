package com.example.store.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class ProductQuantity {

    @NonNull
    private Product product_id;
    @NonNull
    private int quantity;
}
