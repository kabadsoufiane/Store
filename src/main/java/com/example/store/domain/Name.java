package com.example.store.domain;

import lombok.*;

@Builder
@Data
public class Name {
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
}
