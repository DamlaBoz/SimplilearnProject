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
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;
	@Autowired
    private UserService userService;
	@Autowired
    private PurchaseService purchaseService;
	
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products); 
        return "redirect:/dashboard"; 
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping
    public String addProduct(@ModelAttribute Product products) {
        productService.addProduct(products);
        return "redirect:/dashboard"; 
    }
    @GetMapping("edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@PathVariable Long id, Model model) {
        Product products = productService.getProductById(id); 
        model.addAttribute("product", products);        
        return "deneme"; 
    }
    
    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
    	   System.err.println("checkpoint");
        productService.updateProduct(id, product); 
        return "redirect:/deneme"; 
    }
    
    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id ) {
        productService.deleteProduct(id);
        return "redirect:/deneme"; 
    }
    
 
    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/search")
    public String searchProducts(@RequestParam String query, Model model) {
    	System.err.println("Search query:" + query);
        List<Product> productsearch = productService.searchProductsByName(query); 
        System.err.println("Search results" + productsearch);
        model.addAttribute("productsearch", productsearch);	
        return "deneme"; 
    }
    

}
