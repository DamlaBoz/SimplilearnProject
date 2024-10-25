package com.damla.shoestore.shoestore_admin.dao;
import com.damla.shoestore.shoestore_admin.entity.Product;
import com.damla.shoestore.shoestore_admin.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
}
