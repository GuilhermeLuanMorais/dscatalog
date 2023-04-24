package com.main.simpleitstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.simpleitstore.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	
}
