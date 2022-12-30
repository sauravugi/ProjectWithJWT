package com.saurav.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.exceptions.CustomerException;
import com.saurav.exceptions.ProductException;
import com.saurav.models.Customer;
import com.saurav.models.Orders;
import com.saurav.models.Product;
import com.saurav.service.CustomerService;



@RestController
@RequestMapping("/saurav")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer/register")
	public ResponseEntity<Customer> registerCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{
		
		Customer cust = customerService.registerCustomer(customer);
		
		return new ResponseEntity<Customer>(cust,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/customer/update")
	public ResponseEntity<String> updateCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{
		
		String msg = customerService.updateCustomer(customer);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customer/delete/{user}/{password}")
	public ResponseEntity<String> deleteCustomerHandler( @PathVariable String user ,@PathVariable String password) throws CustomerException{
		
		String msg = customerService.deleteCustomer(user, password);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer/login/{user}/{password}")
	public ResponseEntity<String> loginCustomerHandler (@PathVariable String user ,@PathVariable String password) throws CustomerException{
		
		String msg = customerService.loginCustomer(user, password);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Product>> getProductsHandler () throws ProductException{
		
		List<Product> products = customerService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/customer/{amount}")
	public ResponseEntity<List<Product>> getProductsByAmountHandler (@PathVariable Integer amount) throws ProductException{
		
		List<Product> products = customerService.getProductsByAmount(amount);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/customer/order/{id}")
	public ResponseEntity<List<Orders>> getOrdersHandler (@PathVariable Integer id) throws CustomerException{
		
		List<Orders> orders = customerService.getAllOrders(id);
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	
	@GetMapping("/customer/order/{id}/{productId}/{quantity}")
	public ResponseEntity<Orders> getOrdersHandler (@PathVariable Integer id,@PathVariable Integer productId,@PathVariable Integer quantity) throws CustomerException, ProductException{
			
		Orders order = customerService.buyProduct(id, productId, quantity);
			
		return new ResponseEntity<Orders>(order,HttpStatus.OK);
	}

}