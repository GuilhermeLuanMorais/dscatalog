package com.main.simpleitstore.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.simpleitstore.dto.CategoryDTO;
import com.main.simpleitstore.entities.Category;
import com.main.simpleitstore.repositories.CategoryRepository;
import com.main.simpleitstore.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> categories = repository.findAll();
		return categories.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	public CategoryDTO findById(Long id) {
		Optional<Category> optional = repository.findById(id);	
		Category category = new Category();
			category = optional.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return new CategoryDTO(category);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO category) {
		Category entity = new Category();
		entity.setName(category.getName() );
		repository.save(entity);
		return new CategoryDTO(entity);
	}
	
}
 