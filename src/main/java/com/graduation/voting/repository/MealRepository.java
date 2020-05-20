package com.graduation.voting.repository;

import com.graduation.voting.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id =:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m WHERE m.id = :id AND m.restaurant.id = :restaurantId")
    Meal getOneMeal(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId ORDER BY m.date DESC")
    List<Meal> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m WHERE m.date=:date AND m.restaurant.id=:restaurantId ORDER BY m.id")
    List<Meal> getAllByDateAndRestaurant(@Param("date") LocalDate date, @Param("restaurantId") int restaurantId);
}
