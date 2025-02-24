package com.example.library_management.services;

import com.example.library_management.dto.requests.UserRequest;
import com.example.library_management.dto.responses.UserResponse;
import com.example.library_management.entities.User;
import com.example.library_management.exceptions.UserNotFoundException;
import com.example.library_management.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());

        User savedUser = userRepository.save(newUser);
        return convertToUserResponse(savedUser);
    }

    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        return convertToUserResponse(user);
    }

    public UserResponse updateUser(Long id, String name, String email) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        userToUpdate.setName(name);
        userToUpdate.setEmail(email);

        User updatedUser = userRepository.save(userToUpdate);
        return convertToUserResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        userRepository.delete(userToDelete);
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }

    private UserResponse convertToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
