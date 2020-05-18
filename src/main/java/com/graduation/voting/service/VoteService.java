package com.graduation.voting.service;

import com.graduation.voting.model.Vote;
import com.graduation.voting.repository.RestaurantRepository;
import com.graduation.voting.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote create(int userId, int restaurantId) {
        return null;
    }

    public void update(Vote vote, int userId, int restaurantId) {
    }

    public void delete(int id, int userId) {
    }

    public Vote get(int id, int userId) {
        return null;
    }

    public List<Vote> getAll(int userId) {
        return null;
    }
}
