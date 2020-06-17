package com.graduation.voting.repository;

import com.graduation.voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:id ORDER BY v.date desc")
    List<Vote> getAllByUserId(@Param("id") int id);

    List<Vote> findAllByRestaurantIdOrderByDateDescUserAsc(int restaurantId);

    List<Vote> findAllByDateOrderByRestaurantIdAscIdAsc(LocalDate date);
}
