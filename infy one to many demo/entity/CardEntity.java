package com.infy.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



//Credit Card
@Entity
@Table(name="CARD") 
public class CardEntity {
	
	@Id
	@Column(name="CARD_ID")
	private Integer cardId;
	@Column(name="CARD_NUMBER")
	private String cardNumber;
	@Column(name="EXPIRY_DATE")
	private LocalDate expiryDate;
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "CardEntity [cardId=" + cardId + "]";
	}
	
	
	
}
