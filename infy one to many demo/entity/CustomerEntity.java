package com.infy.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer customerId;
	@Column(name="EMAILID")
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cust_id")
	private Set<CardEntity> cardEntities;

	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<CardEntity> getCardEntities() {
		return cardEntities;
	}

	public void setCardEntities(Set<CardEntity> cardEntities) {
		this.cardEntities = cardEntities;
	}

	
	
	
	
}
