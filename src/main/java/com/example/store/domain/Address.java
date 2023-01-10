package com.example.store.domain;

import lombok.*;

import java.util.List;

@Builder
@Data
public class Address {

    @NonNull
    private String country;
    @NonNull
    private String city;
    @NonNull
    private int number;
    @NonNull
    private String zipcode;
    private List<Geolocation> geolocation;
}
