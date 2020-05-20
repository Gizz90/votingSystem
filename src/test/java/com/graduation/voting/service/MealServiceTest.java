package com.graduation.voting.service;

import com.graduation.voting.model.Meal;
import com.graduation.voting.repository.MealRepository;
import com.graduation.voting.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.graduation.voting.MealTestData.*;
import static com.graduation.voting.RestaurantTestData.RESTAURANT1_ID;
import static com.graduation.voting.RestaurantTestData.RESTAURANT2_ID;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService mealService;
    @Autowired
    private MealRepository mealRepository;

    @Test
    public void get() {
        Meal actual = mealService.get(MEAL1_ID, RESTAURANT1_ID);
        MEAL_MATCHER.assertMatch(actual, MEAL1);
    }


    @Test
    public void delete() {
        mealService.delete(MEAL1_ID, RESTAURANT1_ID);
        Assert.assertNull(mealRepository.getOneMeal(MEAL1_ID, RESTAURANT1_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        Assert.assertThrows(NotFoundException.class,
                () -> mealService.delete(1, RESTAURANT1_ID));
    }

    @Test
    public void deleteNotOwn() throws Exception {
        Assert.assertThrows(NotFoundException.class,
                () -> mealService.delete(MEAL1_ID, RESTAURANT2_ID));
    }

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = mealService.create(newMeal, RESTAURANT1_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(newId, RESTAURANT1_ID), newMeal);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        mealService.update(updated, RESTAURANT1_ID);
        MEAL_MATCHER.assertMatch(mealService.get(MEAL1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        NotFoundException ex = Assert.assertThrows(NotFoundException.class,
                () -> mealService.update(MEAL1, RESTAURANT2_ID));
        Assert.assertEquals("Not found entity with id=" + MEAL1_ID, ex.getMessage());
    }

    @Test
    public void getAll() {
        MEAL_MATCHER.assertMatch(mealService.getAll(RESTAURANT1_ID), RESTAURANT1_MEALS);
    }

    @Test
    public void getAllByDate() {
        MEAL_MATCHER.assertMatch(mealService.getAllByDate(LocalDate
                .of(2020, 04, 01), RESTAURANT1_ID), RESTAURANT1_MEALS_2020_04_01);
    }
}