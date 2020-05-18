package com.graduation.voting.service;

import com.graduation.voting.model.Restaurant;
import com.graduation.voting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant get(int id) {
        return null;
    }

    public List<Restaurant> getAllWithCurrentMeals(LocalDate localDate) {
        return null;
    }

    public List<Restaurant> getAll() {
        return null;
    }

    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    public void update(Restaurant restaurant) {
    }

    public void delete(int id) {
    }
}
