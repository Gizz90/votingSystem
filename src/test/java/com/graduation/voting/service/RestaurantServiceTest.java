package com.graduation.voting.service;

import com.graduation.voting.model.Restaurant;
import com.graduation.voting.repository.RestaurantRepository;
import com.graduation.voting.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.graduation.voting.MealTestData.MEAL_MATCHER;
import static com.graduation.voting.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        Restaurant restaurant = restaurantService.get(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void getNotFound() {
        Assert.assertThrows(NotFoundException.class,
                () -> restaurantService.get(1));
    }

    @Test
    public void create() {
        Restaurant newRestaurant = getNew();
        Restaurant created = restaurantService.create(newRestaurant);
        Integer newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        restaurantService.update(updated);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void delete() {
        restaurantService.delete(RESTAURANT1_ID);
        Assert.assertNull(restaurantRepository.findById(RESTAURANT1_ID).orElse(null));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class,
                () -> restaurantService.delete(1));
    }

    @Test
    public void getAll() {
        List<Restaurant> all = restaurantService.getAll();
        RESTAURANT_MATCHER.assertMatch(all, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void getAllWithCurrentMeals() {
        final List<Restaurant> all = restaurantService.getAllWithCurrentMeals(LocalDate.of(2020, 04, 01));
        //RESTAURANT_MATCHER.assertMatch(all, RESTAURANT1, RESTAURANT2);
        all.forEach(restaurant ->
                MEAL_MATCHER.assertMatch(
                        restaurant.getMeals(),
                        mealService.getAllByDate(LocalDate.of(2020, 04, 01),
                                restaurant.getId())));
    }
}