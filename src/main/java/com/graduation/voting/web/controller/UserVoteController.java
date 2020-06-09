package com.graduation.voting.web.controller;

import com.graduation.voting.model.Vote;
import com.graduation.voting.service.VoteService;
import com.graduation.voting.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {

    static final String REST_URL = "/profile/vote";
    private static final Logger log = LoggerFactory.getLogger(UserVoteController.class);

    private final VoteService voteService;

    public UserVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = "/restaurants/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable("restaurantId") int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("voting for restaurantId {} from userId {}", restaurantId, userId);
        Vote created = voteService.vote(userId, restaurantId, LocalTime.now());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}