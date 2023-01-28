package com.example.store.service;

import com.example.store.domain.Address;
import com.example.store.repository.AddressRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public void insertData(@NonNull Address address){
        addressRepository.insertData(address);
    }
    public List<Address> getAddresses(){
        return addressRepository.getAddresses();
    }

    public List<Address> getAddressesById(@NonNull int id_user){
        return addressRepository.getAddressById(id_user);
    }
    public void updateAddressById(@NonNull int id_user, String country, String city, int number, String zipcode,
                                        double latitude, double longitude){
        addressRepository.updateAddressById(id_user, country, city, number, zipcode, latitude, longitude);
    }

    public void deleteAddressById(@NonNull int id_user){
        addressRepository.deleteAddressById(id_user);
    }
}
