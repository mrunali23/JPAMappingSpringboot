package com.myproject.springJpaproject.service;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.springJpaproject.entity.Role;
import com.myproject.springJpaproject.entity.User;
import com.myproject.springJpaproject.repository.RoleRepository;
import com.myproject.springJpaproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    
    public User saveUser(User user) {
        List<Role> rolesToSave = new ArrayList<>();

        for (Role role : user.getRoles()) {
            // Check if the role exists in the database
            Optional<Role> existingRoleOpt = roleRepository.findByRoleName(role.getRoleName());
            System.out.println("Role: " + existingRoleOpt.get().getRoleName());
            log.info("Checking role: {}", role.getRoleName()); // Using log.info()
            if (existingRoleOpt.isPresent()) {
                // If the role exists, use the existing role
            	 System.out.println("Existing Role: " + existingRoleOpt.get().getRoleName());
                rolesToSave.add(existingRoleOpt.get());
            } else {
                // If the role does not exist, save the new role
                Role savedRole = roleRepository.save(role);
                rolesToSave.add(savedRole);
            }
        }

        // Assign the resolved roles to the user
        user.setRoles(rolesToSave);

        // Save the user, which will create entries in the user_roles table
        return userRepository.save(user);
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

     //Get all users
    @Cacheable(value = "userslist", key = "'allUsers'")
    @Transactional 
    public List<User> getAllUsers() {
    	
    	 List<User> users = userRepository.findAll(); // Fetch all users from the database
         
         // Initialize roles for each user
       //  users.forEach(user -> Hibernate.initialize(user.getRoles()));
         
    	  users.forEach(user -> user.getRoles().size());
         return users; 
         
    }
//    
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Transactional
//    @Cacheable("users") // Caching the result of this method
//    public User getAllUsers() {
//    	
//    	 List<User> users = userRepository.findAll();
//        Session session = sessionFactory.getCurrentSession();
//        User user = session.get(User.class, users);
//
//        // Initialize the lazy roles collection
//        Hibernate.initialize(user.getRoles());
//
//        return user;
//    }

    // Get a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update an existing user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setRoles(updatedUser.getRoles());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    
    public List<String> getUsersByRoleName(String roleName) {
        return userRepository.findByRoles_RoleName(roleName).stream().map(x->x.getUsername()).toList();
    }
}
