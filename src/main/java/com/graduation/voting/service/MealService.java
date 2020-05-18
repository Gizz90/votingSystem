package com.graduation.voting.service;

import com.graduation.voting.model.Meal;
import com.graduation.voting.repository.MealRepository;
import com.graduation.voting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;

    public MealService(MealRepository mealRepository, RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal get(int id) {
        return null;
    }

    public void delete(int id) {
    }

    public void update(Meal meal, int restaurantId) {
    }

    public Meal create(Meal meal, int restaurantId) {
        return null;
    }

    public List<Meal> getAll(int restaurantId) {
        return null;
    }

    public List<Meal> getAllByDate(LocalDate date, int restaurantId) {
        return null;
    }

}
