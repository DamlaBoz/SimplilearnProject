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
public class UserController {
	@Autowired
    private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PurchaseService purchaseService;
    @GetMapping("/deneme2")
    public String deneme2(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.err.println(authentication);
        System.err.println(authentication.getName());
        if(userService.findByEmail(authentication.getName()).getRole().toString() == "ADMIN") {
        	List<User> users = userService.getAllUsers(); 
            model.addAttribute("users", users);  
        	return "deneme2";
        }else {
            List<Purchase> purchases = purchaseService.getPurchasesByUser(userService.findByEmail(authentication.getName()));
            model.addAttribute("purchases", purchases);
        	List<Product> products = productService.getAllProducts(); 
            model.addAttribute("products", products); 
        	return "deneme2";
        }
    } 
    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/user/search")
    public String searchUsers(@RequestParam String query, Model model) {
    	System.err.println("Search query:" + query);
        List<User> usersearch = userService.searchUserByName(query); 
        System.err.println("Search results" + usersearch);
        model.addAttribute("usersearch", usersearch);	
        return "deneme2"; 
    }
}
