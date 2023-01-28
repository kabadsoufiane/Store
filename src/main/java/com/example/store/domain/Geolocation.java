package com.example.store.domain;

import lombok.*;

@Builder
@Data
public class Geolocation {

    @NonNull
    private double lat;
    @NonNull
    private double longitude;
}
