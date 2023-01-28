package com.example.store.service;

import com.example.store.domain.Address;
import com.example.store.domain.User;
import com.example.store.repository.AddressRepository;
import com.example.store.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public List<User> getUserById(@NonNull int id_user){
        return userRepository.getUserById(id_user);
    }

    public void updateUserById(@NonNull int id_user, String email, String password,
                               String first_name, String last_name, int phone){
        userRepository.updateUserById(id_user, email, password, first_name, last_name, phone);
    }

    public void deleteUserById(@NonNull int id_user){
        userRepository.deleteUserById(id_user);
    }
}
