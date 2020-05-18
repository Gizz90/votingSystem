package com.graduation.voting.service;

import com.graduation.voting.model.User;
import com.graduation.voting.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return null;
    }

    public void update(User user) {
    }

    public void delete(int id) {
    }

    public User get(int id) {
        return null;
    }

    public User getByEmail(String email) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }
}
