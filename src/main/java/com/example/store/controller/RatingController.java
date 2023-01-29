package com.example.store.controller;

import com.example.store.domain.Rating;
import com.example.store.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RatingController {

    @Autowired
    RatingsService ratingsService;

    @PostMapping("/addRatings")
    public ResponseEntity<Rating> addRatings(@RequestBody Rating request){
        try {
            ratingsService.insertRatings(request);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping("/allRatings")
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratings = ratingsService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/getRatingsById/{id_product}")
    public ResponseEntity<List<Rating>> getRatingsById(@PathVariable int id_product){
        List<Rating> ratings = ratingsService.getRatingsById(id_product);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @PutMapping("/updateProductById/{id_product}")
    public ResponseEntity<Rating> updateRatingsById(@PathVariable int id_product,@RequestBody Rating request){
        try {
            ratingsService.updateRatingsById(id_product, request.getCount(), request.getRate());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_product}")
    public ResponseEntity<Void> deleteRatingsById(@PathVariable int id_product){
        ratingsService.deleteRatingsById(id_product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
