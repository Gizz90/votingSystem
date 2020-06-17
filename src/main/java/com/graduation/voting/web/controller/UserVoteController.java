package com.graduation.voting.web.controller;

import com.graduation.voting.model.Vote;
import com.graduation.voting.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;

import static com.graduation.voting.util.ValidationUtil.assureIdConsistent;
import static com.graduation.voting.util.ValidationUtil.checkNew;
import static com.graduation.voting.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {

    static final String REST_URL = "/profile";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService voteService;

    public UserVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping(value = "/votes")
    public List<Vote> getAllByUser() {
        int userId = authUserId();
        log.info("get votes for user with id = {}", userId);
        return voteService.getAllByUserId(userId);
    }

    @PostMapping(value = "restaurants/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@Valid @RequestBody Vote vote, @PathVariable("restaurantId") int restaurantId) {
        int userId = authUserId();
        checkNew(vote);
        log.info("vote for restaurantId {} from userId {}", restaurantId, userId);
        Vote created = voteService.create(vote, userId, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/restaurants/" + restaurantId + "/votes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "restaurants/{restaurantId}/votes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Vote vote, @PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {
        int userId = authUserId();
        assureIdConsistent(vote, id);
        log.info("update vote for restaurantId {} from userId {}", restaurantId, userId);
        voteService.update(vote, userId, restaurantId, LocalTime.now());
    }
}