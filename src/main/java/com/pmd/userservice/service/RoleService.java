package com.pmd.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmd.userservice.model.Role;
import com.pmd.userservice.repository.RoleRepository;

@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  public Optional<Role> getRoleById(String id) {
    return roleRepository.findById(id);
  }

  public Role createRole(Role role) {
    return roleRepository.save(role);
  }

  public Optional<Role> updateRole(String id, Role roleDetails) {
    return roleRepository.findById(id)
        .map(role -> {
          role.setName(roleDetails.getName());
          return roleRepository.save(role);
        });
  }

  public boolean deleteRole(String id) {
    return roleRepository.findById(id)
        .map(role -> {
          roleRepository.delete(role);
          return true;
        })
        .orElse(false);
  }
}