package com.infy.model;

import java.time.LocalDate;


public class Loan {
	
	private Integer loanId;
	private Double amount;
	private LocalDate loanIssueDate;
	private Customer customer;
	
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getLoanIssueDate() {
		return loanIssueDate;
	}
	public void setLoanIssueDate(LocalDate loanIssueDate) {
		this.loanIssueDate = loanIssueDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
