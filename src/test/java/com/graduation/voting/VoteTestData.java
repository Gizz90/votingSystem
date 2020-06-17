package com.graduation.voting;

import com.graduation.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static com.graduation.voting.RestaurantTestData.RESTAURANT1;
import static com.graduation.voting.RestaurantTestData.RESTAURANT2;
import static com.graduation.voting.UserTestData.*;
import static com.graduation.voting.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator(Vote.class, "user", "restaurant");

    public static final int VOTE1_ID = START_SEQ + 20;
    public static final int VOTE2_ID = START_SEQ + 21;

    public static final Vote VOTE1_ADMIN_RESTAURANT1 = new Vote(VOTE1_ID, of(2020, 04, 01), USER_ADMIN, RESTAURANT2);
    public static final Vote VOTE2_USER1_RESTAURANT2 = new Vote(VOTE1_ID + 1, of(2020, 04, 01), USER1, RESTAURANT1);
    public static final Vote VOTE3_USER2_RESTAURANT1 = new Vote(VOTE1_ID + 2, of(2020, 04, 01), USER2, RESTAURANT1);
    public static final Vote VOTE4_ADMIN_RESTAURANT2 = new Vote(VOTE1_ID + 3, now(), USER_ADMIN, RESTAURANT2);
    public static final Vote VOTE5_USER2_RESTAURANT2 = new Vote(VOTE1_ID + 4, now(), USER2, RESTAURANT2);

    //    public static final List<Vote> ADMIN_VOTES = List.of(VOTE1_ADMIN_RESTAURANT1,VOTE4_ADMIN_RESTAURANT2);
    public static final List<Vote> USER1_VOTES = List.of(VOTE2_USER1_RESTAURANT2);
    public static final List<Vote> RESTAURANT2_VOTES = List.of(VOTE4_ADMIN_RESTAURANT2, VOTE5_USER2_RESTAURANT2, VOTE1_ADMIN_RESTAURANT1);
    public static final List<Vote> VOTES_ON_2020_04_01 = List.of(VOTE2_USER1_RESTAURANT2, VOTE3_USER2_RESTAURANT1, VOTE1_ADMIN_RESTAURANT1);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), USER1, RESTAURANT2);
    }

    public static Vote getNewOnDate(LocalDate localDate) {
        return new Vote(null, localDate, USER1, RESTAURANT2);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE2_USER1_RESTAURANT2.getId(), LocalDate.now(), USER1, RESTAURANT2);
    }

}

