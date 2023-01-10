package com.example.store.domain;

import lombok.*;

@Builder
@Data
public class Rating {

    private int count;
    private double rate;
}
