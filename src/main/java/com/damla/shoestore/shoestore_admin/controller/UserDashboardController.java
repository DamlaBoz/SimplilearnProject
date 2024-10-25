package com.damla.shoestore.shoestore_admin.controller;

import com.damla.shoestore.shoestore_admin.entity.Product;
import com.damla.shoestore.shoestore_admin.entity.Purchase;
import com.damla.shoestore.shoestore_admin.entity.User;
import com.damla.shoestore.shoestore_admin.service.ProductService;
import com.damla.shoestore.shoestore_admin.service.PurchaseService;
import com.damla.shoestore.shoestore_admin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user-dashboard")
public class UserDashboardController {


    @Autowired
    private ProductService productService;
	@Autowired
    private UserService userService;
	@Autowired
    private PurchaseService purchaseService;
	
    @GetMapping()
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products); 
        return "user-dashboard"; 
    }

    

}
