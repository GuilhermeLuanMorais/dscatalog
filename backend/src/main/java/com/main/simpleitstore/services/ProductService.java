package com.main.simpleitstore.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.simpleitstore.dto.ProductDTO;
import com.main.simpleitstore.entities.Product;
import com.main.simpleitstore.repositories.ProductRepository;
import com.main.simpleitstore.services.exceptions.DatabaseException;
import com.main.simpleitstore.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> categories = repository.findAll(pageRequest);
		return categories.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> optional = repository.findById(id);	
		Product product = new Product();
		product = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new ProductDTO(product, product.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO Product) {
		Product entity = new Product();
		entity.setName(Product.getName());
		/*entity.setName(Product.getName() );
		entity.setName(Product.getName() );
		entity.setName(Product.getName() );
		entity.setName(Product.getName() );*/
		
		repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO Product) {
		try {
			Product entity = repository.getOne(id);
			entity.setName(Product.getName());
			/*entity.setName(Product.getName() );
			entity.setName(Product.getName() );
			entity.setName(Product.getName() );
			entity.setName(Product.getName() );*/
			
			entity = repository.save(entity);
			
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("integrity violation");
		}
		
	}
	
}
 