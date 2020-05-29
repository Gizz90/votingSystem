package com.graduation.voting.web.controller;

import com.graduation.voting.model.Restaurant;
import com.graduation.voting.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController {

    static final String REST_URL = "/profile/restaurants";
    private static final Logger log = LoggerFactory.getLogger(UserRestaurantController.class);

    private final RestaurantService restaurantService;

    public UserRestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAllCurrent() {
        log.info("get all restaurants with meals");
        return restaurantService.getAllWithCurrentMeals(LocalDate.now());
    }
}
