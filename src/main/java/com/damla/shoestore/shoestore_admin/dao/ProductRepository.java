package com.damla.shoestore.shoestore_admin.dao;
import com.damla.shoestore.shoestore_admin.entity.Product;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContainingIgnoreCase(String name);
}

