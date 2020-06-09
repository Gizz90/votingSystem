package com.graduation.voting.web.controller;

import com.graduation.voting.model.User;
import com.graduation.voting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.graduation.voting.util.ValidationUtil.assureIdConsistent;
import static com.graduation.voting.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(ProfileController.REST_URL)
public class ProfileController {

    static final String REST_URL = "/profile";
    protected final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        int id = authUserId();
        log.info("get {}", id);
        return userService.get(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        int id = authUserId();
        log.info("delete {}", id);
        userService.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        int id = authUserId();
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        userService.update(user);
    }
}
