package com.example.store.service;

import com.example.store.domain.Address;
import com.example.store.domain.User;
import com.example.store.repository.AddressRepository;
import com.example.store.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserRespository userRespository;
    @Autowired
    AddressRepository addressRepository;

    public void insertUser(User user, Address address){
        userRespository.insertData(user);
        addressRepository.insertData(address);
    }

}
