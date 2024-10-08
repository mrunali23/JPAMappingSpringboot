package com.myproject.springJpaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.myproject.springJpaproject.entity.Role;
import com.myproject.springJpaproject.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Get a role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Get all roles
  //  @Cacheable(value = "rolelist", key = "'allRoles'")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Update an existing role
    public Role updateRole(Long id, Role updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setRoleName(updatedRole.getRoleName());
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    // Delete a role by ID
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
