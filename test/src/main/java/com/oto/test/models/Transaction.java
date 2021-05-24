package com.oto.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity  
public class Transaction {

	@Id
	@GeneratedValue
	private Integer tid;
	
	@NotNull
	private String product;  
	@NotNull
	private int quantity;
	@NotNull
	private double price;
	private double line_total;

	
}
