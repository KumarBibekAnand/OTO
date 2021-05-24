package com.oto.test.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



//mark class as an Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity  
public class Invoice {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private String customer; 
	private Date date;
	private int total_quatity;
	private double total_amount;
	
	@OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id",referencedColumnName = "id")
	private List<Transaction> transactions = new ArrayList<Transaction>();

}
