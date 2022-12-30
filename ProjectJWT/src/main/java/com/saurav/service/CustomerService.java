package com.saurav.service;

import java.util.List;

import com.saurav.exceptions.CustomerException;
import com.saurav.exceptions.ProductException;
import com.saurav.models.Customer;
import com.saurav.models.Orders;
import com.saurav.models.Product;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer) throws CustomerException;
	
	public String updateCustomer(Customer customer) throws CustomerException;
	
	public String deleteCustomer(String username,String password) throws CustomerException;
	
	public String loginCustomer(String username,String password) throws CustomerException;

	public List<Product> getAllProducts() throws ProductException;
	
	public Orders buyProduct(Integer id,Integer productId,Integer quantity) throws CustomerException,ProductException;
	
	public List<Product> getProductsByAmount(Integer amount) throws ProductException;
	
	public List<Orders> getAllOrders(Integer id) throws CustomerException;
}
