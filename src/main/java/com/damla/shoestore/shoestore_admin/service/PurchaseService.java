package com.damla.shoestore.shoestore_admin.service;

import com.damla.shoestore.shoestore_admin.dao.PurchaseRepository;
import com.damla.shoestore.shoestore_admin.entity.Purchase;
import com.damla.shoestore.shoestore_admin.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase); 
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAllByOrderByPurchaseDateDesc();
    }
    
    public List<Purchase> getPurchasesByUser(User user) {
        return purchaseRepository.findByUser(user);
    }
   
    public List<Purchase> filterPurchasesByCategory(String category) {
        return purchaseRepository.findByProductCategory(category);
    }
}
