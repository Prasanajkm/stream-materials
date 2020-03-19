package com.infy.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.CustomerLoanDAO;
import com.infy.model.Loan;
 
@Repository(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService {
	
	
	@Autowired
	private CustomerLoanDAO loanDAO;

	@Override
	public Loan getLoanDetails(Integer loanId) throws Exception {
		// TODO Auto-generated method stub
		Loan loan=loanDAO.getLoanDetails(loanId);
		if(loan==null){
			throw new Exception("Service.LOAN_UNAVAILABLE");
		}
		return loan;
	}

	@Override
	public Integer addLoanDetails(Loan loan) throws Exception {
		// TODO Auto-generated method stub
		
		return loanDAO.addOnlyLoanDetails(loan);
	}

	@Override
	public Integer addLoanAndCustomer(Loan loan) throws Exception {
		// TODO Auto-generated method stub
		return loanDAO.addLoanAndCustomer(loan);
	}

	@Override
	public Integer allocateNewLoanToExistingCustomer(Integer customerId,Loan loan)
			throws Exception {
		// TODO Auto-generated method stub
		
		return loanDAO.allocateNewLoanToExistingCustomer(customerId, loan);
		
	}

	@Override
	public void deleteLoan(Integer loanId) throws Exception {
		// TODO Auto-generated method stub
		Loan loan=loanDAO.getLoanDetails(loanId);
		if(loan==null){
			throw new Exception("Service.LOAN_UNAVAILABLE");
		}
		loanDAO.deleteLoan(loanId);
	}

	@Override
	public void closeLoan(Integer loanId) throws Exception {

		loanDAO.closeLoan(loanId);
		
	}

}