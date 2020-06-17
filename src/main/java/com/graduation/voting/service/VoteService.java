package com.graduation.voting.service;

import com.graduation.voting.model.Vote;
import com.graduation.voting.repository.RestaurantRepository;
import com.graduation.voting.repository.UserRepository;
import com.graduation.voting.repository.VoteRepository;
import com.graduation.voting.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.graduation.voting.util.ValidationUtil.checkDateTime;
import static com.graduation.voting.util.ValidationUtil.checkNotFoundWithId;
import static java.time.LocalDate.*;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(voteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null), id);
    }

    @Transactional
    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        vote.setDate(now());
        return voteRepository.save(vote);
    }

    @Transactional
    public void update(Vote vote, int userId, int restaurantId, LocalTime time) {
        Assert.notNull(vote, "vote must not be null");
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            throw new NotFoundException("Not found entity with id" + vote.getId());
        }
        checkDateTime(vote.getDate(), time);
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        checkNotFoundWithId(voteRepository.save(vote), userId);
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id) != 0, id);
    }

    public List<Vote> getAllByUserId(int userId) {
        return checkNotFoundWithId(voteRepository.getAllByUserId(userId), userId);
    }

    public List<Vote> getAllByRestaurantId(int restaurantId) {
        return checkNotFoundWithId(voteRepository.findAllByRestaurantIdOrderByDateDescUserAsc(restaurantId), restaurantId);
    }

    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.findAllByDateOrderByRestaurantIdAscIdAsc(date);
    }
}
