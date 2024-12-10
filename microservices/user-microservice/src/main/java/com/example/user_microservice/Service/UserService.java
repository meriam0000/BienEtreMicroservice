package com.example.user_microservice.Service;

import com.example.user_microservice.Entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User updateUser(Long id, User userDetails);

    void deleteUser(Long id);
}
