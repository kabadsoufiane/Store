package com.example.store.controller;

import com.example.store.domain.Address;
import com.example.store.domain.User;
import com.example.store.service.AddressService;
import com.example.store.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UsersService usersService;
    @Autowired
    AddressService addressService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id_user}")
    public ResponseEntity<List<User>> getUsers(@PathVariable int id_user){
        List<User> users = usersService.getUserById(id_user);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/insertuser")
    public ResponseEntity<User> insertUser(@RequestBody User user, @RequestBody Address address) {
        try {
            usersService.insertUser(user, address);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/updateCart/{id_user}")
    public ResponseEntity<User> updateCart(@PathVariable int id_user, @RequestBody User request,
                                           @RequestBody Address request1) {

        try {
            usersService.updateUserById(id_user, request.getEmail(), request.getPassword(), request.getNames()
                    .get(0).getFirstname(), request.getNames().get(0).getLastname(), request.getPhone());
            addressService.updateAddressById(id_user, request1.getCountry(), request1.getCity(),
                    request1.getNumber(), request1.getZipcode(), request1.getGeolocation().get(0).getLat(),
                    request1.getGeolocation().get(0).getLongitude());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
