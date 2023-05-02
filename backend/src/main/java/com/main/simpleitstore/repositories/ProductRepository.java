package com.main.simpleitstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.simpleitstore.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	
}
