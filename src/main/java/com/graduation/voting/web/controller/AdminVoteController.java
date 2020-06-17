package com.graduation.voting.web.controller;

import com.graduation.voting.model.Vote;
import com.graduation.voting.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController {

    public static final String REST_URL = "/admin";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final VoteService voteService;

    public AdminVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping(value = "/restaurants/votes/onDate")
    public List<Vote> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get votes on date {}", date);
        return voteService.getAllByDate(date);
    }

    @GetMapping(value = "/restaurants/{restaurantId}/votes")
    public List<Vote> getAllByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        log.info("get votes by restaurant with id={}", restaurantId);
        return voteService.getAllByRestaurantId(restaurantId);
    }

    @DeleteMapping("/votes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vote with id={}", id);
        voteService.delete(id);
    }

}
