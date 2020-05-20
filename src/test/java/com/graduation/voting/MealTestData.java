package com.graduation.voting;

import com.graduation.voting.model.AbstractBaseEntity;
import com.graduation.voting.model.Meal;

import java.util.List;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class MealTestData {
    public static TestMatcher<Meal> MEAL_MATCHER = TestMatcher.usingFieldsComparator("restaurant");

    public static final int MEAL1_ID = AbstractBaseEntity.START_SEQ + 6;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, "chicken noodles", 1000, of(2020, 4, 1));
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, "roast beef", 1500, of(2020, 4, 1));
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, "flounder", 800, of(2020, 4, 1));
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, "pizza", 1000, of(2020, 4, 1));
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, "green tea", 200, of(2020, 4, 1));
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, "vegetable soup", 1000, of(2020, 4, 1));
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, "frankfurters", 1400, of(2020, 4, 1));
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7, "shrimps", 1100, of(2020, 4, 1));
    public static final Meal MEAL9 = new Meal(MEAL1_ID + 8, "spaghetti", 800, of(2020, 4, 1));
    public static final Meal MEAL10 = new Meal(MEAL1_ID + 9, "cappuccino", 150, of(2020, 4, 1));
    public static final Meal MEAL11 = new Meal(MEAL1_ID + 10, "fish soup", 1000, of(2020, 4, 2));
    public static final Meal MEAL12 = new Meal(MEAL1_ID + 11, "schnitzel", 1800, of(2020, 4, 2));
    public static final Meal MEAL13 = new Meal(MEAL1_ID + 12, "salmon", 1300, of(2020, 4, 2));
    public static final Meal MEAL14 = new Meal(MEAL1_ID + 13, "omelette", 500, of(2020, 4, 2));
    public static final Meal MEAL15 = new Meal(MEAL1_ID + 14, "coffee", 100, of(2020, 4, 2));

    public static final List<Meal> RESTAURANT1_MEALS = List.of(MEAL11, MEAL12, MEAL13, MEAL1, MEAL2, MEAL3, MEAL4, MEAL5);
    public static final List<Meal> RESTAURANT1_MEALS_2020_04_01 = List.of(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5);
    public static final List<Meal> RESTAURANT2_MEALS = List.of(MEAL6, MEAL7, MEAL8, MEAL9, MEAL10);

    public static Meal getNew() {
        return new Meal(null, "newMeal", 1000, now());
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, "updatedMeal", 1500, MEAL1.getDate());
    }

}
