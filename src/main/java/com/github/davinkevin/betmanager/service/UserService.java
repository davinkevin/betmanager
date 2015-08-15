package com.github.davinkevin.betmanager.service;

import com.github.davinkevin.betmanager.entity.User;
import com.github.davinkevin.betmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
@Service
public class UserService {
    final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}
