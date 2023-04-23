package com.main.simpleitstore.dto;

import java.io.Serializable;

import com.main.simpleitstore.entities.Category;

public class CategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	public CategoryDTO() {
	}
	
	public CategoryDTO(Long id, String name) {
		super();
		this.setId(id);
		this.setName(name);
	}
	
	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
