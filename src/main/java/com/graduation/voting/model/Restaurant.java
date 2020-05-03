package com.graduation.voting.model;

import java.util.List;

public class Restaurant extends AbstractNamedEntity {

    private List<Meal> meals;

    public Restaurant(Integer id, String name, List<Meal> meals) {
        super(id, name);
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
