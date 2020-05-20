package com.graduation.voting.service;

import com.graduation.voting.model.Vote;
import com.graduation.voting.repository.VoteRepository;
import com.graduation.voting.util.exception.NotFoundException;
import com.graduation.voting.util.exception.VoteException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.graduation.voting.util.DateTimeUtil.EXPIRED_TIME;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public VoteService(VoteRepository voteRepository, RestaurantService restaurantService, UserService userService) {
        this.voteRepository = voteRepository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @Transactional
    public Vote vote(Integer userId, Integer restaurantId, LocalTime time) {
        Optional<Vote> votes = voteRepository.findByUserIdAndDateVoting(userId, LocalDate.now());
        if (restaurantService.getAllWithCurrentMeals(LocalDate.now()).stream()
                .noneMatch(restaurant -> Objects.equals(restaurant.getId(), restaurantId))) {
            throw new NotFoundException("Restaurant don't have meal today");
        }
        return voteRepository.save(votes.map(v -> {
            if (time.isAfter(EXPIRED_TIME)) {
                throw new VoteException("it is after 11:00. it is too late, vote can't be changed");
            }
            v.setRestaurant(restaurantService.get(restaurantId));
            return v;
        }).orElse(new Vote(LocalDate.now(), userService.get(userId),
                restaurantService.get(restaurantId))));
    }

    public List<Vote> getAllByRestaurantId(int restId) {
        return voteRepository.findAllByRestaurantIdOrderByDateDesc(restId);
    }

    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.findAllByDateOrderByRestaurantIdAsc(date);
    }
}
