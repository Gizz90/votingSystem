package com.graduation.voting.service;

import com.graduation.voting.model.Vote;
import com.graduation.voting.repository.RestaurantRepository;
import com.graduation.voting.repository.UserRepository;
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
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Vote vote(Integer userId, Integer restaurantId, LocalTime time) {
        Optional<Vote> votes = voteRepository.findByUserIdAndDate(userId, LocalDate.now());
        if (restaurantRepository.getAllCurrent(LocalDate.now()).stream()
                .noneMatch(restaurant -> Objects.equals(restaurant.getId(), restaurantId))) {
            throw new NotFoundException("Restaurant don't have meal today");
        }
        return voteRepository.save(votes.map(v -> {
            if (time.isAfter(EXPIRED_TIME)) {
                throw new VoteException("it is after 11:00. it is too late, vote can't be changed");
            }
            v.setRestaurant(restaurantRepository.getOne(restaurantId));
            return v;
        }).orElse(new Vote(LocalDate.now(), userRepository.getOne(userId),
                restaurantRepository.getOne(restaurantId))));
    }

    public List<Vote> getAllByRestaurantId(int restId) {
        return voteRepository.findAllByRestaurantIdOrderByDateDesc(restId);
    }

    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.findAllByDateOrderByRestaurantIdAsc(date);
    }
}
