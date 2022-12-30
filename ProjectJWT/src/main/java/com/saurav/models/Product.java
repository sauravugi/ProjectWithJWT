package com.saurav.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotNull
	private String productName;
	
	@NotNull
	private Integer price;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private String category;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
	List<Orders> orderList = new ArrayList<>();
	

}
