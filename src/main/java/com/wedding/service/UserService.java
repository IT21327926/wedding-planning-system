package com.wedding.service;

import com.wedding.model.User;
import com.wedding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user, String confirmPassword) {
        if (!user.getPassword().equals(confirmPassword)) {
            return null;
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return null;
        }
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}