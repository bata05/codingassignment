package com.devstaff.codingassignment.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Farm {
	@Id
	@GeneratedValue
	private long transactionId;
	
	private String acres;
	
	private String cropType;
	
	private String season;
	
	private int plantedCount;
	
	private int plantedCurrentCount = 0;
	
	private int harvested = 0;
	
	private Date  creationDate;
	
	private Date  updatedDate;
	
}
