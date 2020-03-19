package com.infy.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ADHAR_CARD")
public class AdharCardEntity {

	@Id
	@Column(name="ADHARCARDNUMBER")
	private Long adharCardNumber;
	private String address;
	private String phoneNumber;

	public Long getAdharCardNumber() {
		return adharCardNumber;
	}

	public void setAdharCardNumber(Long adharCardNumber) {
		this.adharCardNumber = adharCardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}


