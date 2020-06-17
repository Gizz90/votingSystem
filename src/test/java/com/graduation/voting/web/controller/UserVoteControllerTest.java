package com.graduation.voting.web.controller;

import com.graduation.voting.VoteTestData;
import com.graduation.voting.model.Vote;
import com.graduation.voting.service.VoteService;
import com.graduation.voting.util.exception.VoteException;
import com.graduation.voting.web.AbstractControllerTest;
import com.graduation.voting.web.json.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static com.graduation.voting.RestaurantTestData.RESTAURANT1_ID;
import static com.graduation.voting.RestaurantTestData.RESTAURANT2_ID;
import static com.graduation.voting.TestUtil.readFromJson;
import static com.graduation.voting.UserTestData.USER1_ID;
import static com.graduation.voting.VoteTestData.*;
import static com.graduation.voting.util.ValidationUtil.isTimeExpired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserVoteController.REST_URL + "/";

    @Autowired
    VoteService voteService;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "votes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(USER1_VOTES));
    }

    @Test
    void create() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "restaurants/" + RESTAURANT1_ID + "/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = readFromJson(action, Vote.class);
        int newId = created.getId();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.get(newId, USER1_ID), newVote);
    }

    @Test
    void update() throws Exception {
        Vote updated = VoteTestData.getUpdated();
        if (!isTimeExpired()) {
            perform(MockMvcRequestBuilders.put(REST_URL + "restaurants/" + RESTAURANT1_ID + "/votes/" + VOTE2_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(updated)))
                    .andDo(print())
                    .andExpect(status().isNoContent());

            Vote actual = voteService.get(updated.getId(), USER1_ID);
            assertEquals(VOTE2_USER1_RESTAURANT2.getDate(), actual.getDate());
            VOTE_MATCHER.assertMatch(actual, updated);
        } else {
            Assertions.assertThrows(VoteException.class,
                    () -> voteService.update(updated, USER1_ID, RESTAURANT2_ID, LocalTime.now()));
        }
    }
}