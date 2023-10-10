package com.main.simpleitstore.tests;

import java.time.Instant;

import com.main.simpleitstore.dto.ProductDTO;
import com.main.simpleitstore.entities.Category;
import com.main.simpleitstore.entities.Product;

public class Factory {

	public static Product createProduct() {
		Product product = new Product(1L, "Phone", "New Phone", 800.0, "https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
		product.getCategories().add(new Category(2L, "Electronics"));
		
		return product;
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		
		return new ProductDTO(product, product.getCategories());
	}
}
