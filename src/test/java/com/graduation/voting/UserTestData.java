package com.graduation.voting;

import com.graduation.voting.model.User;
import com.graduation.voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

import static com.graduation.voting.model.AbstractBaseEntity.START_SEQ;
import static com.graduation.voting.model.Role.ADMIN;
import static com.graduation.voting.model.Role.USER;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "password");

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

    public static final int USER1_ID = START_SEQ;
    public static final int USER_ADMIN_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ + 2;

    public static final User USER_ADMIN = new User(USER_ADMIN_ID, "Admin", "admin@gmail.com", "admin", ADMIN, USER);
    public static final User USER1 = new User(USER1_ID, "User", "user@yandex.ru", "password", USER);
    public static final User USER2 = new User(USER2_ID, "Mike", "newuser@gmail.com", "newuser", USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", new Date(), Collections.singleton(USER));
    }

    public static User getUpdated() {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        return updated;
    }

    public static User getUpdatedAdmin() {
        User updated = new User(USER_ADMIN);
        updated.setName("UpdatedName");
        return updated;
    }
}
