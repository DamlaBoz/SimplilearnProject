package com.damla.shoestore.shoestore_admin.controller;


import com.damla.shoestore.shoestore_admin.entity.Product;
import com.damla.shoestore.shoestore_admin.entity.Purchase;
import com.damla.shoestore.shoestore_admin.entity.User;
import com.damla.shoestore.shoestore_admin.service.ProductService;
import com.damla.shoestore.shoestore_admin.service.PurchaseService;
import com.damla.shoestore.shoestore_admin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PurchaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/deneme3")
    public String deneme(
            @RequestParam(value = "category", required = false) String category, 
            Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.err.println(authentication.getName());
        System.err.println(authentication.getAuthorities());

        List<Purchase> purchases;

        // If the user is an admin
        if (authentication.getAuthorities().toString().contains("ADMIN")) {
            // If a category is selected, filter by category
            if (category != null && !category.isEmpty()) {
                purchases = purchaseService.filterPurchasesByCategory(category);
            } else {
                purchases = purchaseService.getAllPurchases();
            }
        } else {
            // For non-admin users, show only their purchases
            purchases = purchaseService.getPurchasesByUser(userService.findByEmail(authentication.getName()));
        }

        // Add the purchases and products to the model
        model.addAttribute("purchases", purchases);

        // Always fetch products for display
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "deneme3";
    }
}
