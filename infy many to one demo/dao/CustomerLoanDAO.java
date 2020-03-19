package com.infy.dao;

import com.infy.model.Loan;



public interface CustomerLoanDAO {

	public Loan getLoanDetails(Integer loanId) throws Exception;
	public Integer addOnlyLoanDetails(Loan loan) throws Exception;
	public Integer addLoanAndCustomer(Loan loan) throws Exception;
	public Integer allocateNewLoanToExistingCustomer(Integer customerId,Loan loan) throws Exception;
	public void deleteLoan(Integer loanId) throws Exception;
	public void closeLoan(Integer loanId) throws Exception;

}