package com.graduation.voting.service;

import com.graduation.voting.model.Role;
import com.graduation.voting.model.User;
import com.graduation.voting.repository.UserRepository;
import com.graduation.voting.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static com.graduation.voting.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() throws Exception {
        User newUser = getNew();
        User created = userService.create(newUser);
        Integer newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        userService.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        userService.delete(USER_ID);
        Assert.assertNull(userRepository.findById(USER_ID).orElse(null));
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        userService.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = userService.get(USER_ID);
        USER_MATCHER.assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        userService.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = userService.getByEmail("user@yandex.ru");
        USER_MATCHER.assertMatch(user, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        userService.update(updated);
        USER_MATCHER.assertMatch(userService.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = userService.getAll();
        USER_MATCHER.assertMatch(all, ADMIN, USER, USER2);
    }
}