package com.graduation.voting.web.controller;

import com.graduation.voting.model.Meal;
import com.graduation.voting.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.graduation.voting.util.ValidationUtil.assureIdConsistent;
import static com.graduation.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMealController {

    static final String REST_URL = "/admin";
    private static final Logger log = LoggerFactory.getLogger(AdminMealController.class);

    private final MealService mealService;

    public AdminMealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(value = "/restaurants/{restaurantId}/meals/{id}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("get meal with id={}", id);
        return mealService.get(id, restaurantId);
    }

    @PostMapping(value = "/restaurants/{restaurantId}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> save(@Valid @RequestBody Meal meal, @PathVariable("restaurantId") int restaurantId) {
        checkNew(meal);
        log.info("save meal in restaurant with id={}", restaurantId);
        Meal created = mealService.create(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/restaurants/{restaurantId}/meals/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/restaurants/{restaurantId}/meals/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Meal meal, @PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {
        assureIdConsistent(meal, id);
        log.info("update meal with id={}", id);
        mealService.update(meal, restaurantId);
    }

    @DeleteMapping(value = "/restaurants/{restaurantId}/meals/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("delete meal with id={}", id);
        mealService.delete(id, restaurantId);
    }

    @GetMapping(value = "/restaurants/{restaurantId}/meals")
    public List<Meal> getAll(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all meals of restaurant with id={}", restaurantId);
        return mealService.getAll(restaurantId);
    }

    @GetMapping(value = "/restaurants/{restaurantId}/meals/onDate")
    public List<Meal> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                   @PathVariable("restaurantId") int restaurantId) {
        log.info("get all meals of restaurant with id={}", restaurantId);
        return mealService.getAllByDate(date, restaurantId);
    }
}
