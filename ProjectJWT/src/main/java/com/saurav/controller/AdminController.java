package com.saurav.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.configuration.SecurityUser;
import com.saurav.configuration.jwt.JwtUtils;
import com.saurav.exceptions.AdminException;
import com.saurav.exceptions.CustomerException;
import com.saurav.exceptions.OrderException;
import com.saurav.exceptions.ProductException;
import com.saurav.models.Admin;
import com.saurav.models.Customer;
import com.saurav.models.Orders;
import com.saurav.models.Product;
import com.saurav.payload.request.LoginRequest;
import com.saurav.payload.response.JwtResponse;
import com.saurav.payload.response.Message;
import com.saurav.repository.AdminRepo;
import com.saurav.service.AdminService;

@RestController
@RequestMapping("/saurav")
public class AdminController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private AdminService adminService;
	
	
	@CrossOrigin
	@PostMapping("/admin/register")
	public ResponseEntity<Message> registerAdminHandler(@Valid @RequestBody Admin admin) throws AdminException{
		
		Message msg = adminService.registerAdmin(admin);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
		
	}
	
	/*
	@GetMapping("/admin/login/{user}/{password}")
	public ResponseEntity<String> loginAdminHandler (@PathVariable String user ,@PathVariable String password) throws AdminException{
		
		String msg = adminService.loginAdmin(user, password);
		
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	*/
	
	  @CrossOrigin
	  @PostMapping("/admin/login")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();

	    return ResponseEntity.ok(new JwtResponse(jwt,
					    		"Bearer",
					    		userDetails.getUsername(),  
	                         	"ADMIN"));
	  }

	@CrossOrigin
	@PostMapping("/admin/update")
	public ResponseEntity<Message> updateAdminHandler(@Valid @RequestBody Admin admin) throws AdminException{
		
		Message msg = adminService.updateAdmin(admin);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
	}
	  
	@CrossOrigin
	@DeleteMapping("/admin/delete/{user}/{password}")
	public ResponseEntity<Message> deleteAdminHandler (@PathVariable String user ,@PathVariable String password) throws AdminException{
		
		Message msg = adminService.deleteAdmin(user, password);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@GetMapping("/admin/orders")
	public ResponseEntity<List<Orders>> getOrdersHandler () throws OrderException{
		
		List<Orders> orders = adminService.getAllOrders();
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/admin/orders/{month}")
	public ResponseEntity<List<Orders>> getMonthOrdersHandler (@PathVariable Integer month) throws OrderException{
		
		List<Orders> orders = adminService.getMonthWiseOrders(month);
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/admin/dayorders/{day}")
	public ResponseEntity<List<Orders>> getDayOrdersHandler (@PathVariable Integer day) throws OrderException{
		
		List<Orders> orders = adminService.getDayWiseOrders(day);
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/admin/products")
	public ResponseEntity<List<Product>> getProductsHandler () throws ProductException{
		
		List<Product> products = adminService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/admin/products")
	public ResponseEntity<Message> registerProductHandler(@Valid @RequestBody Product product) throws ProductException{
		
		Message msg = adminService.addProduct(product);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@GetMapping("/admin/leastproducts")
	public ResponseEntity<List<Product>> getLeastProductsHandler () throws ProductException{
		
		List<Product> products = adminService.leastQuantityProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> getCustomersHandler () throws CustomerException{
		
		List<Customer> customers = adminService.getAllCustomers();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/admin/customers/{id}")
	public ResponseEntity<Customer> getCustomerHandler (@PathVariable Integer id) throws CustomerException{
		
		Customer customer = adminService.getCustomerDetailsById(id);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	

}

