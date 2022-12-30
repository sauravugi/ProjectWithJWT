package com.saurav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saurav.exceptions.ProductException;
import com.saurav.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	@Query(value = "select * from product where quantity < 10;" , nativeQuery = true)
	List<Product> getLeastProducts() throws ProductException;
	
	List<Product> findByPriceLessThanEqual(Integer amount)throws ProductException;
	
//	@Query(value = "SELECT * FROM product WHERE price < :maxPrice", nativeQuery = true)
//    public List<Product> getProductsWithMaxPrice(Integer maxPrice) throws ProductException;

}
