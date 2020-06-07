package com.graduation.voting;

import com.graduation.voting.model.AbstractBaseEntity;
import com.graduation.voting.model.Restaurant;

import static com.graduation.voting.MealTestData.RESTAURANT1_MEALS;
import static com.graduation.voting.MealTestData.RESTAURANT2_MEALS;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class, "meals");

    public static final int RESTAURANT1_ID = AbstractBaseEntity.START_SEQ + 3;
    public static final int RESTAURANT2_ID = AbstractBaseEntity.START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Restaurant2");

    static {
        RESTAURANT1.setMeals(RESTAURANT1_MEALS);
        RESTAURANT1.setMeals(RESTAURANT2_MEALS);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "new Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "updated Name");
    }
}
