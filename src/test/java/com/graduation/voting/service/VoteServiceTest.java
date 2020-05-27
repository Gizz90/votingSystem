package com.graduation.voting.service;

import com.graduation.voting.model.Vote;
import com.graduation.voting.util.exception.VoteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.graduation.voting.RestaurantTestData.RESTAURANT2_ID;
import static com.graduation.voting.UserTestData.USER1_ID;
import static com.graduation.voting.UserTestData.USER2_ID;
import static com.graduation.voting.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void vote() {
        Vote created = getNew();
        Vote newVote = voteService.vote(USER2_ID, RESTAURANT2_ID, LocalTime.now());
        created.setId(newVote.getId());
        VOTE_MATCHER.assertMatch(created, newVote);
    }

    @Test
    public void voteBeforeExpiredTime() {
        voteService.vote(USER1_ID, RESTAURANT2_ID, LocalTime.of(10,00));
        voteService.vote(USER1_ID, RESTAURANT2_ID, LocalTime.of(10,59));
    }
    @Test
    public void voteAfterExpiredTime() {
        Assertions.assertThrows(VoteException.class,
                () -> voteService.vote(USER1_ID, RESTAURANT2_ID, LocalTime.of(11,00).plus(1, ChronoUnit.NANOS)));
    }

    @Test
    public void getAllByRestaurantId() {
        List<Vote> all = voteService.getAllByRestaurantId(RESTAURANT2_ID);
        VOTE_MATCHER.assertMatch(all, RESTAURANT2_VOTES);
    }

    @Test
    public void getAllByDate() {
        List<Vote> all = voteService.getAllByDate(LocalDate.of(2020,04,01));
        VOTE_MATCHER.assertMatch(all, VOTES_ON_2020_04_01);
    }
}