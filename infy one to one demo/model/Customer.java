package com.infy.model;
 
import java.time.LocalDate;

/**
 *  This is a bean class. Also called as model class has the attributes to keep customer properties
 *
 */
/**
 * @author ETA_JAVA
 *
 */

public class Customer {
	
	private Integer customerId;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	private AdharCard adharcard;
	
	public AdharCard getAdharcard() {
		return adharcard;
	}
	public void setAdharcard(AdharCard adharcard) {
		this.adharcard = adharcard;
	}
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
}
