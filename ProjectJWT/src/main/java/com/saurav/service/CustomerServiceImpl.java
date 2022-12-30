package com.saurav.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurav.exceptions.*;
import com.saurav.models.*;
import com.saurav.repository.*;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {

		if(customer == null) throw new CustomerException("Enter Valid Customer....!");
		
		Customer cust = customerRepo.save(customer);
		
		return cust;
	}

	@Override
	public String updateCustomer(Customer customer) throws CustomerException {

		if(customer == null) throw new CustomerException("Enter Valid Customer....!");
		
		Optional<Customer> opt = customerRepo.findByEmail(customer.getEmail());
		
		if(opt.isEmpty()) throw new CustomerException("Enter Valid Email...!");
		
		if(opt.get().getPassword().equals(customer.getPassword()))
				customerRepo.save(customer);
		else
			throw new CustomerException("Enter Valid User Password...!");
		
		return "Updated Sucessfully with Id "+opt.get().getCustomerId();

	}

	@Override
	public String deleteCustomer(String username, String password) throws CustomerException {
		
		Optional<Customer> opt = customerRepo.findByEmail(username);
		
		if(opt.isEmpty()) throw new CustomerException("Enter Valid Email...!");
		
		if(opt.get().getPassword().equals(password))
			customerRepo.deleteById(opt.get().getCustomerId());
		else
			throw new CustomerException("Enter Valid User Password...!");
		
		return "Delete Sucessfully with Id "+opt.get().getCustomerId();
	}

	@Override
	public String loginCustomer(String username, String password) throws CustomerException {
		
		Optional<Customer> opt = customerRepo.findByEmail(username);
		
		if(opt.isEmpty()) throw new CustomerException("Enter Valid Email...!");
		
		if(opt.get().getPassword().equals(password))
			return "Customer Login Sucessfully....!";
		else
			throw new CustomerException("Enter Valid User Password...!");
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {
		
		List<Product> products = productRepo.findAll();
		
		if(products.size()==0) throw new ProductException("NO Product Available...!");
		
		return products;
	}

	@Override
	public Orders buyProduct(Integer id, Integer productId,Integer quantity) throws CustomerException, ProductException {

		Optional<Customer> opt = customerRepo.findById(id);
		
		if(opt.isEmpty()) throw new CustomerException("Wrong Customer id...!");
		
		Optional<Product> opt2 = productRepo.findById(productId);
		
		if(opt.isEmpty()) throw new ProductException("Wrong Product id...!");
		
		if((int)opt2.get().getQuantity() < (int)quantity) throw new ProductException("Stock is less than your quantity..!");
		
		opt2.get().setQuantity((int)opt2.get().getQuantity()-(int)quantity);
		
		productRepo.save(opt2.get());
		
		Integer amount = (int)opt2.get().getPrice()*(int)quantity;
		
		Orders order = new Orders();
		order.setCustomerid(id);
		order.setCustomer(opt.get());
		order.setProduct(opt2.get());
		order.setProductid(productId);
		order.setTotalBill(amount);
		order.setQuantity(quantity);
		order.setProductName(opt2.get().getProductName());
		
		opt.get().getOrders().add(order);
		
		opt2.get().getOrderList().add(order);
		
		Orders od = orderRepo.save(order);
		
		
		return od;
	}

	@Override
	public List<Product> getProductsByAmount(Integer amount) throws ProductException {
		
		List<Product> products = productRepo.findByPriceLessThanEqual(amount);
		
		if(products.size()==0) throw new ProductException("NO Product Available...!");
		
		return products;
	}

	@Override
	public List<Orders> getAllOrders(Integer id) throws CustomerException {
		
		Optional<Customer> opt = customerRepo.findById(id);
		
		if(opt.isEmpty()) throw new CustomerException("Wrong Customer id...!");
		
		if(opt.get().getOrders().size()==0) throw new CustomerException("No order done by customer");
		
		return opt.get().getOrders();
	}

}