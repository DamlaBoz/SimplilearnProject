package com.damla.shoestore.shoestore_admin.dao;
import com.damla.shoestore.shoestore_admin.entity.Purchase;
import com.damla.shoestore.shoestore_admin.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByOrderByPurchaseDateDesc();
    List<Purchase> findByUser(User user);
    
    List<Purchase> findByProductCategory(String category);
}
