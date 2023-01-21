package com.example.store.service;

import com.example.store.domain.Address;
import com.example.store.domain.User;
import com.example.store.repository.AddressRepository;
import com.example.store.repository.UserRepository;
import com.example.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    public void insertUser(User user, Address address){
        userRepository.insertData(user);
        addressRepository.insertData(address);
    }

}
