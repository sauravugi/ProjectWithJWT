package com.saurav.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saurav.exceptions.AdminException;
import com.saurav.exceptions.CustomerException;
import com.saurav.exceptions.OrderException;
import com.saurav.exceptions.ProductException;
import com.saurav.models.Admin;
import com.saurav.models.Customer;
import com.saurav.models.Orders;
import com.saurav.models.Product;
import com.saurav.payload.response.Message;
import com.saurav.repository.AdminRepo;
import com.saurav.repository.CustomerRepo;
import com.saurav.repository.OrderRepo;
import com.saurav.repository.ProductRepo;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public Message registerAdmin(Admin admin) throws AdminException {
		
		if(admin==null) throw new AdminException("Enter Valid Admin Details..!");
		
		admin.setPassword(encoder.encode(admin.getPassword()));
		
		adminRepo.save(admin);
		
		return new Message("Register Sucessfully....! "+admin.getEmail());
	}

	@Override
	public Message loginAdmin(String username, String password) throws AdminException {
		
		Optional<Admin> opt = adminRepo.findByEmail(username);
		
		if(opt.isEmpty()) throw new AdminException("Enter Valid Admin Email..!");
		
		if(opt.get().getPassword().equals(password)==false)  throw new AdminException("Enter Valid Admin Password..!");
		
		return new Message("Login Sucessfully with Id "+opt.get().getAdminId());
	}

	@Override
	public Message deleteAdmin(String username, String password) throws AdminException {
		
		Optional<Admin> opt = adminRepo.findByEmail(username);
		
		if(opt.isEmpty()) throw new AdminException("Enter Valid Admin Email..!");
		
		if(opt.get().getPassword().equals(password)==false)  throw new AdminException("Enter Valid Admin Password..!");
		
		adminRepo.deleteById(opt.get().getAdminId());
		
		return new Message("Delete Sucessfully with Id "+opt.get().getAdminId());
	}

	@Override
	public Message updateAdmin(Admin admin) throws AdminException {
		
		Optional<Admin> opt = adminRepo.findById(admin.getAdminId());
		
		if(opt.isEmpty()) throw new AdminException("Invalid Admin details");
		
		adminRepo.save(admin);
		
		return new Message("Updated Sucessfully with Id "+admin.getAdminId());
		
	}

	@Override
	public List<Orders> getAllOrders() throws OrderException {
		
		List<Orders> orders = orderRepo.findAll();
		
		if(orders.size()==0) throw new OrderException("Not Any order Available in the list...!");
		
		return orders;
	}

	@Override
	public List<Orders> getDayWiseOrders(Integer day) throws OrderException {
		
		List<Orders> orders = orderRepo.getDateWiseOrders(day);
		
		if(orders.size()==0) throw new OrderException("Not Any order Available in the list...!");
		
		return orders;
	}

	@Override
	public List<Orders> getMonthWiseOrders(Integer month) throws OrderException {

		List<Orders> orders = orderRepo.getMonthWiseOrders(month);
		
		if(orders.size()==0) throw new OrderException("Not Any order Available in the list...!");
		
		return orders;
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {
		
		List<Product> products = productRepo.findAll();
		
		if(products.size()==0) throw new ProductException("NO Product Available...!");
		
		return products;
	}

	@Override
	public Message addProduct(Product product) throws ProductException {
		
		if(product == null) throw new ProductException("Enter Valid Product Details");
		
		productRepo.save(product);
		
		return new Message("Sucessfully Added "+product.getProductName());
	}

	@Override
	public List<Product> leastQuantityProducts() throws ProductException {
		
		List<Product> products = productRepo.getLeastProducts();
		
		if(products.size()==0) throw new ProductException("NO Product Available...!");
		
		return products;
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerException {
		
		List<Customer> customers = customerRepo.findAll();
		
		if(customers.size()==0) throw new CustomerException("NO Product Available...!");
		
		return customers;
	}

	@Override
	public Customer getCustomerDetailsById(Integer id) throws CustomerException {

		Optional<Customer> customer = customerRepo.findById(id);
		
		if(customer.isEmpty()) throw new CustomerException("Wrong customer id");
		
		return customer.get();
	}

}
