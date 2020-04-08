package com.omnicuris.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omnicuris.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	public Product findByItemId(int itemId);
}
