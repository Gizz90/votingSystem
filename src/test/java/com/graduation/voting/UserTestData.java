package com.graduation.voting;

import com.graduation.voting.model.User;

import java.util.Collections;
import java.util.Date;

import static com.graduation.voting.model.AbstractBaseEntity.START_SEQ;
import static com.graduation.voting.model.Role.ROLE_ADMIN;
import static com.graduation.voting.model.Role.ROLE_USER;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator("registered", "roles");

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ + 2;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", ROLE_ADMIN, ROLE_USER);
    public static final User USER1 = new User(USER1_ID, "User", "user@yandex.ru", "password", ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "Mike", "newuser@gmail.com", "mike", ROLE_USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", new Date(), Collections.singleton(ROLE_USER));
    }

    public static User getUpdated() {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        return updated;
    }
}
