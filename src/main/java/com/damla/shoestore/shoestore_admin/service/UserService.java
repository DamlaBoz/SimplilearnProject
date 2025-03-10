package com.damla.shoestore.shoestore_admin.service;

import com.damla.shoestore.shoestore_admin.entity.User;
import com.damla.shoestore.shoestore_admin.entity.Product;
import com.damla.shoestore.shoestore_admin.entity.Role;
import com.damla.shoestore.shoestore_admin.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createAdmin(User admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }
    public List<User> searchUserByName(String query) {
        return userRepository.findByNameContainingIgnoreCase(query); 
    }
}
