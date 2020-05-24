package com.graduation.voting.repository;

import com.graduation.voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByUserIdAndDate(Integer userId, LocalDate now);

    List<Vote> findAllByRestaurantIdOrderByDateDescUserAsc(int restaurantId);

    List<Vote> findAllByDateOrderByRestaurantIdAscIdAsc(LocalDate date);
}
