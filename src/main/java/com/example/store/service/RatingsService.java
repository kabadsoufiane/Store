package com.example.store.service;

import com.example.store.domain.Rating;
import com.example.store.repository.RatingsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {
    @Autowired
    RatingsRepository ratingsRepository;

    public List<Rating> getAllRatings(){
        return ratingsRepository.getRatings();
    }

    public List<Rating> getRatingsById(@NonNull int id_product){
        return ratingsRepository.getRatingById(id_product);
    }

    public void updateRatingsById(@NonNull int id_product, int count, double rate){
        ratingsRepository.updateRatingById(id_product, count, rate);
    }

    public void deleteRatingsBuId(@NonNull int id_product){
        ratingsRepository.deleteRatingsById(id_product);
    }
}
