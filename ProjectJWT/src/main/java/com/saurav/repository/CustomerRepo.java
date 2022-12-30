package com.saurav.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurav.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByEmail(String email);

}
