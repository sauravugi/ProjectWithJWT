package com.saurav.service;

import java.util.List;

import com.saurav.exceptions.AdminException;
import com.saurav.exceptions.CustomerException;
import com.saurav.exceptions.OrderException;
import com.saurav.exceptions.ProductException;
import com.saurav.models.Admin;
import com.saurav.models.Customer;
import com.saurav.models.Orders;
import com.saurav.models.Product;
import com.saurav.payload.response.Message;

public interface AdminService {
	
	public Message registerAdmin(Admin admin) throws AdminException;
	
	public Message loginAdmin(String username,String password) throws AdminException;
	
	public Message deleteAdmin(String username,String password) throws AdminException;
	
	public Message updateAdmin(Admin admin) throws AdminException;
	
	public List<Orders> getAllOrders() throws OrderException;
	
	public List<Orders> getDayWiseOrders(Integer day) throws OrderException;
	
	public List<Orders> getMonthWiseOrders(Integer month) throws OrderException;
	
	public List<Product> getAllProducts() throws ProductException;
	
	public Message addProduct(Product product) throws ProductException;
	
	public List<Product> leastQuantityProducts() throws ProductException;
	
	public List<Customer> getAllCustomers() throws CustomerException;
	
	public Customer getCustomerDetailsById(Integer id) throws CustomerException;

}
