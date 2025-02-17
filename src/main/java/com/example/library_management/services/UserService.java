package com.example.library_management.services;

import com.example.library_management.entities.User;
import com.example.library_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);

        return userRepository.save(newUser);
    }

    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    public User updateUser(Long id, String name, String email) {
        User userToUpdtade = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        userToUpdtade.setName(name);
        userToUpdtade.setEmail(email);

        return userRepository.save(userToUpdtade);
    }

    public User deleteUser(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
        userRepository.delete(userToDelete);
        return userToDelete;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
