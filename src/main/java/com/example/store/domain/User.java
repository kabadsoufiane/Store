package com.example.store.domain;

import lombok.*;

import java.util.List;

@Builder
@Data
public class User {

    @NonNull
    private int id_user;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private List<Name> names;
    @NonNull
    private int phone;
    private List<Address> address;

}
