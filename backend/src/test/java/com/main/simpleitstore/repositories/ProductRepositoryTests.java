package com.main.simpleitstore.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.main.simpleitstore.entities.Product;
import com.main.simpleitstore.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {
	
	private Long existingId;
	private long nonExistingId;
	private long countTotalProducts;
	
	@Autowired
	private ProductRepository productRepository;
	
	@BeforeEach
	void setUp() {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Product product = Factory.createProduct();
		
		product.setId(null);
		product = productRepository.save(product);
		
		Assertions.assertNotNull(product);
		Assertions.assertEquals(countTotalProducts + 1, product.getId());
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		productRepository.deleteById(existingId);
		Optional<Product> result = productRepository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			productRepository.deleteById(nonExistingId);
		});
	}
	
	@Test
	public void findShouldNotReturnWhenIdDoesNotExist() {
		Optional<Product> product = productRepository.findById(nonExistingId);
		Assertions.assertFalse(product.isPresent());
	}
	
	@Test
	public void findShouldReturnWhenIdDoesExist() {
		Optional<Product> product = productRepository.findById(existingId);
		Assertions.assertTrue(product.isPresent());
	}
	
}
