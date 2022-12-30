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

import com.saurav.exceptions.AdminException;
import com.saurav.exceptions.CustomerException;
import com.saurav.exceptions.OrderException;
import com.saurav.exceptions.ProductException;
import com.saurav.models.Admin;
import com.saurav.models.Customer;
import com.saurav.models.Orders;
import com.saurav.models.Product;
import com.saurav.service.AdminService;

@RestController
@RequestMapping("/saurav")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/admin/register")
	public ResponseEntity<String> registerAdminHandler(@Valid @RequestBody Admin admin) throws AdminException{
		
		String msg = adminService.registerAdmin(admin);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/login/{user}/{password}")
	public ResponseEntity<String> loginAdminHandler (@PathVariable String user ,@PathVariable String password) throws AdminException{
		
		String msg = adminService.loginAdmin(user, password);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/admin/update")
	public ResponseEntity<String> updateAdminHandler(@Valid @RequestBody Admin admin) throws AdminException{
		
		String msg = adminService.updateAdmin(admin);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/delete/{user}/{password}")
	public ResponseEntity<String> deleteAdminHandler (@PathVariable String user ,@PathVariable String password) throws AdminException{
		
		String msg = adminService.deleteAdmin(user, password);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/orders")
	public ResponseEntity<List<Orders>> getOrdersHandler () throws OrderException{
		
		List<Orders> orders = adminService.getAllOrders();
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/admin/orders/{month}")
	public ResponseEntity<List<Orders>> getMonthOrdersHandler (@PathVariable Integer month) throws OrderException{
		
		List<Orders> orders = adminService.getMonthWiseOrders(month);
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/admin/dayorders/{day}")
	public ResponseEntity<List<Orders>> getDayOrdersHandler (@PathVariable Integer day) throws OrderException{
		
		List<Orders> orders = adminService.getDayWiseOrders(day);
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/admin/products")
	public ResponseEntity<List<Product>> getProductsHandler () throws ProductException{
		
		List<Product> products = adminService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@PostMapping("/admin/products")
	public ResponseEntity<String> registerProductHandler(@Valid @RequestBody Product product) throws ProductException{
		
		String msg = adminService.addProduct(product);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/leastproducts")
	public ResponseEntity<List<Product>> getLeastProductsHandler () throws ProductException{
		
		List<Product> products = adminService.leastQuantityProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> getCustomersHandler () throws CustomerException{
		
		List<Customer> customers = adminService.getAllCustomers();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/admin/customers/{id}")
	public ResponseEntity<Customer> getCustomerHandler (@PathVariable Integer id) throws CustomerException{
		
		Customer customer = adminService.getCustomerDetailsById(id);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	

}