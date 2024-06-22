package com.pmd.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pmd.userservice.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User findByUsername(String username);

  User findByEmail(String email);
}