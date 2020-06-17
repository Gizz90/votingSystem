package com.graduation.voting.service;

import com.graduation.voting.VoteTestData;
import com.graduation.voting.model.Vote;
import com.graduation.voting.repository.VoteRepository;
import com.graduation.voting.util.exception.NotFoundException;
import com.graduation.voting.util.exception.VoteException;
import com.graduation.voting.web.AbstractControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.graduation.voting.RestaurantTestData.RESTAURANT1_ID;
import static com.graduation.voting.RestaurantTestData.RESTAURANT2_ID;
import static com.graduation.voting.UserTestData.*;
import static com.graduation.voting.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class VoteServiceTest extends AbstractControllerTest {

    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteRepository voteRepository;

    @Test
    void get() {
        Vote actual = voteService.get(VOTE1_ID, USER_ADMIN_ID);
        VOTE_MATCHER.assertMatch(actual, VOTE1_ADMIN_RESTAURANT1);
    }

    @Test
    void create() {
        Vote created = VoteTestData.getNew();
        Vote newVote = voteService.create(created, USER2_ID, RESTAURANT1_ID);
        created.setId(newVote.getId());
        VOTE_MATCHER.assertMatch(created, newVote);
    }

    @Test
    void updateBeforeExpiredTime() {
        Vote updated = VoteTestData.getUpdated();
        voteService.update(updated, USER1_ID, RESTAURANT2_ID, LocalTime.of(10, 59));
    }

    @Test
    void updateAfterExpiredTime() {
        Vote updated = VoteTestData.getUpdated();
        Assertions.assertThrows(VoteException.class,
                () -> voteService.update(updated, USER1_ID, RESTAURANT2_ID, LocalTime.of(11, 00).plus(1, ChronoUnit.NANOS)));
    }

    @Test
    public void updateNotFound() throws Exception {
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> voteService.update(VOTE1_ADMIN_RESTAURANT1, USER2_ID, RESTAURANT2_ID, LocalTime.now()));
        Assertions.assertEquals("Not found entity with id=" + VOTE1_ID, ex.getMessage());
    }

    @Test
    void delete() {
        voteService.delete(VOTE1_ID);
        Assertions.assertNull(voteRepository.findById(VOTE1_ID).orElse(null));
    }

    @Test
    public void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> voteService.delete(1));
    }

    @Test
    void getAllByRestaurantId() {
        List<Vote> all = voteService.getAllByRestaurantId(RESTAURANT2_ID);
        VOTE_MATCHER.assertMatch(all, RESTAURANT2_VOTES);
    }

    @Test
    void getAllByDate() {
        List<Vote> all = voteService.getAllByDate(LocalDate.of(2020, 04, 01));
        VOTE_MATCHER.assertMatch(all, VOTES_ON_2020_04_01);
    }

    @Test
    void getAllByUserId() {
        List<Vote> allByUser = voteService.getAllByUserId(USER1_ID);
        VOTE_MATCHER.assertMatch(allByUser, USER1_VOTES);
    }

}