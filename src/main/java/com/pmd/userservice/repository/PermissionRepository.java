package com.pmd.userservice.repository;

import com.pmd.userservice.model.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {
  Permission findByName(String name);
}