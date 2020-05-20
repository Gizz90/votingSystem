package com.graduation.voting.service;

import com.graduation.voting.model.Meal;
import com.graduation.voting.repository.MealRepository;
import com.graduation.voting.repository.RestaurantRepository;
import com.graduation.voting.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.graduation.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;

    public MealService(MealRepository mealRepository, RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal get(int id, int restaurantId) {
        return checkNotFoundWithId(mealRepository.findById(id)
                .filter(meal -> meal.getRestaurant().getId() == restaurantId)
                .orElse(null), id);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(mealRepository.delete(id, restaurantId) != 0, id);
    }

    @Transactional
    public void update(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            throw new NotFoundException("Not found entity with id" + meal.getId());
        }
        meal.setRestaurant(restaurantRepository.getOne(restaurantId));
        meal.setDate(LocalDate.now());
        checkNotFoundWithId(mealRepository.save(meal), meal.getId());
    }

    @Transactional
    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        meal.setRestaurant(restaurantRepository.getOne(restaurantId));
        meal.setDate(LocalDate.now());
        return mealRepository.save(meal);
    }

    public List<Meal> getAll(int restaurantId) {
        return mealRepository.getAllByRestaurant(restaurantId);
    }

    public List<Meal> getAllByDate(LocalDate date, int restaurantId) {
        return mealRepository.getAllByDateAndRestaurant(date, restaurantId);
    }

}
