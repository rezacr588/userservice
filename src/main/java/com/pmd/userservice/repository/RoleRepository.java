package com.pmd.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pmd.userservice.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
  Role findByName(String name);
}