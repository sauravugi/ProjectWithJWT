package com.saurav.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.saurav.models.Admin;
import com.saurav.models.Customer;
import com.saurav.models.Employee;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Employee emp;

	public SecurityUser(Employee emp) {
		super();
		this.emp = emp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthos = new ArrayList<>();
		
		if(emp instanceof Admin) {
			Admin admin = (Admin)emp;
			SimpleGrantedAuthority grantAuth = new SimpleGrantedAuthority(admin.getRole());
			grantedAuthos.add(grantAuth);
			return grantedAuthos;
		}else {
			Customer customer = (Customer)emp;
			SimpleGrantedAuthority grantAuth = new SimpleGrantedAuthority(customer.getRole());
			grantedAuthos.add(grantAuth);
			return grantedAuthos;
			
		}
	}

	@Override
	public String getPassword() {
		
		if(emp instanceof Admin) {
			Admin admin = (Admin)emp;
			return admin.getPassword();
		}else{
			Customer customer = (Customer)emp;
			return customer.getPassword();
		}
	}

	@Override
	public String getUsername() {
		
		if(emp instanceof Admin) {
			Admin admin = (Admin)emp;
			return admin.getEmail();
		}else{
			Customer customer = (Customer)emp;
			return customer.getEmail();
		}
			
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
