package com.pmd.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmd.userservice.model.Permission;
import com.pmd.userservice.repository.PermissionRepository;

@Service
public class PermissionService {

  @Autowired
  private PermissionRepository permissionRepository;

  public List<Permission> getAllPermissions() {
    return permissionRepository.findAll();
  }

  public Optional<Permission> getPermissionById(String id) {
    return permissionRepository.findById(id);
  }

  public Permission createPermission(Permission permission) {
    return permissionRepository.save(permission);
  }

  public Optional<Permission> updatePermission(String id, Permission permissionDetails) {
    return permissionRepository.findById(id)
        .map(permission -> {
          permission.setName(permissionDetails.getName());
          permission.setDescription(permissionDetails.getDescription());
          return permissionRepository.save(permission);
        });
  }

  public boolean deletePermission(String id) {
    return permissionRepository.findById(id)
        .map(permission -> {
          permissionRepository.delete(permission);
          return true;
        })
        .orElse(false);
  }
}