package com.damla.shoestore.shoestore_admin;
import com.damla.shoestore.shoestore_admin.entity.Role;
import com.damla.shoestore.shoestore_admin.entity.User;
import com.damla.shoestore.shoestore_admin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Admin implements CommandLineRunner{


    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();     
        
        User u = userService.findByEmail("admin@example.com");
        if(u==null) {	        	
	        admin.setEmail("admin@example.com");         
	        admin.setPassword("admin123"); 
	        admin.setName("Admin");
	        admin.setSurname("User");
	        userService.createAdmin(admin);
        }
    }
}