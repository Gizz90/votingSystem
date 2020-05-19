package com.graduation.voting.repository;

import com.graduation.voting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.meals m WHERE m.date=:localDate")
    List<Restaurant> getAllCurrent(@Param("localDate")LocalDate localDate);
}
