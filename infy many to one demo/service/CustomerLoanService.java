package com.infy.service;

import com.infy.model.Loan;

public interface CustomerLoanService {
 
	public Loan getLoanDetails(Integer loanId) throws Exception;
	public Integer addLoanDetails(Loan loan) throws Exception;
	public Integer addLoanAndCustomer(Loan loan) throws Exception;
	public Integer allocateNewLoanToExistingCustomer(Integer customerId,Loan loan) throws Exception;
	public void deleteLoan(Integer loanId) throws Exception;
	public void closeLoan(Integer loanId) throws Exception;
	
   
   
}