package com.damla.shoestore.shoestore_admin.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.damla.shoestore.shoestore_admin.service.ProductService;
import com.damla.shoestore.shoestore_admin.service.PurchaseService;
import com.damla.shoestore.shoestore_admin.service.UserService;
import com.damla.shoestore.shoestore_admin.entity.Product;
import com.damla.shoestore.shoestore_admin.entity.Purchase;
import com.damla.shoestore.shoestore_admin.entity.User;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {


	@Autowired
    private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PurchaseService purchaseService;
    @GetMapping
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.err.println(authentication);
        System.err.println(authentication.getName());
        if(userService.findByEmail(authentication.getName()).getRole().toString() == "ADMIN") {
        	List<Product> products = productService.getAllProducts(); 
            model.addAttribute("products", products);   
        	List<User> users = userService.getAllUsers(); 
            model.addAttribute("users", users); 
            List<Purchase> purchases = purchaseService.getAllPurchases();
            model.addAttribute("purchases", purchases); 
        	return "/admin/admin-dashboard";
        }else {
            List<Purchase> purchases = purchaseService.getPurchasesByUser(userService.findByEmail(authentication.getName()));
            model.addAttribute("purchases", purchases);
        	List<Product> products = productService.getAllProducts(); 
            model.addAttribute("products", products); 
        	return "user-dashboard";
        }
    } 
}

