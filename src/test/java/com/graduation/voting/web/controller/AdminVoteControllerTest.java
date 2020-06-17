package com.graduation.voting.web.controller;

import com.graduation.voting.service.VoteService;
import com.graduation.voting.util.exception.NotFoundException;
import com.graduation.voting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.graduation.voting.RestaurantTestData.RESTAURANT2_ID;
import static com.graduation.voting.TestUtil.userHttpBasic;
import static com.graduation.voting.UserTestData.USER_ADMIN;
import static com.graduation.voting.UserTestData.USER_ADMIN_ID;
import static com.graduation.voting.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminVoteController.REST_URL + "/";

    @Autowired
    VoteService voteService;

    @Test
    void getAllByRestaurantId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "restaurants/" + RESTAURANT2_ID + "/votes")
                .with(userHttpBasic(USER_ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(RESTAURANT2_VOTES));
    }

    @Test
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "restaurants/votes/onDate?date=2020-04-01")
                .with(userHttpBasic(USER_ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTES_ON_2020_04_01));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "votes/" + VOTE1_ID)
                .with(userHttpBasic(USER_ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.get(VOTE1_ID, USER_ADMIN_ID));
    }
}