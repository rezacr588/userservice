package com.pmd.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmd.userservice.model.User;
import com.pmd.userservice.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(String id) {
    return userRepository.findById(id);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public Optional<User> updateUser(String id, User userDetails) {
    return userRepository.findById(id)
        .map(user -> {
          user.setUsername(userDetails.getUsername());
          user.setEmail(userDetails.getEmail());
          user.setRole(userDetails.getRole());
          user.setPermissions(userDetails.getPermissions());
          user.setOnline(userDetails.isOnline());
          return userRepository.save(user);
        });
  }

  public boolean deleteUser(String id) {
    return userRepository.findById(id)
        .map(user -> {
          userRepository.delete(user);
          return true;
        })
        .orElse(false);
  }
}