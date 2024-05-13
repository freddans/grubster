package com.example.grubster.service;

import com.example.grubster.entity.User;
import com.example.grubster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        User newUser = new User(user.getName(), user.getAddress(), user.getPhone(), user.getEmail());
        userRepository.save(newUser);

        return newUser;
    }

    public User findUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            return user;
        } else {

            return null;
        }
    }
}
