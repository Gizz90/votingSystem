package com.graduation.voting.service;

import com.graduation.voting.model.User;
import com.graduation.voting.repository.UserRepository;
import com.graduation.voting.to.UserTo;
import com.graduation.voting.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.graduation.voting.util.ValidationUtil.checkNotFound;
import static com.graduation.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(user);
    }

    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        userRepository.save(UserUtil.updateFromTo(user, userTo));
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id) != 0, id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
