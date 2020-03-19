package com.infy.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infy.entity.CustomerEntity;
import com.infy.entity.LoanEntity;
import com.infy.model.Customer;
import com.infy.model.Loan;

@Repository(value = "customerLoanDAO")
public class CustomerLoanDAOImpl implements CustomerLoanDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Loan getLoanDetails(Integer loanId) throws Exception {
		// TODO Auto-generated method stub
		LoanEntity loanEntity=entityManager.find(LoanEntity.class, loanId);
		Loan loan=null;
		if(loanEntity!=null){
			loan=new Loan();
			loan.setAmount(loanEntity.getAmount());
			loan.setLoanId(loanEntity.getLoanId());
			loan.setLoanIssueDate(loanEntity.getIssueDate());

			CustomerEntity customerEntity=loanEntity.getCustomer();
			if(customerEntity!=null){
				Customer customer=new Customer();
				customer.setCustomerId(customerEntity.getCustomerId());
				customer.setDateOfBirth(customerEntity.getDateOfBirth());
				customer.setEmailId(customerEntity.getEmailId());
				customer.setName(customerEntity.getName());

				loan.setCustomer(customer);
			}
		}
		return loan;
	}

	@Override
	public Integer addOnlyLoanDetails(Loan loan) throws Exception {
		// TODO Auto-generated method stub
		LoanEntity loanEntity=new LoanEntity();
		loanEntity.setAmount(loan.getAmount());
		loanEntity.setIssueDate(loan.getLoanIssueDate());

		entityManager.persist(loanEntity);

		return loanEntity.getLoanId();
	}

	@Override
	public Integer addLoanAndCustomer(Loan loan) throws Exception {
		// TODO Auto-generated method stub
		LoanEntity loanEntity=new LoanEntity();
		loanEntity.setAmount(loan.getAmount());
		loanEntity.setIssueDate(loan.getLoanIssueDate());

		Customer customer=loan.getCustomer();

		CustomerEntity customerEntity=new CustomerEntity();
		customerEntity.setCustomerId(customer.getCustomerId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setName(customer.getEmailId());

		loanEntity.setCustomer(customerEntity);

		entityManager.persist(loanEntity);

		return loanEntity.getLoanId();
	}

	@Override
	public Integer allocateNewLoanToExistingCustomer(Integer customerId , Loan loan)
			throws Exception {
		// TODO Auto-generated method stub

		LoanEntity loanEntity=new LoanEntity();
		loanEntity.setAmount(loan.getAmount());
		loanEntity.setIssueDate(loan.getLoanIssueDate());
		
		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, customerId);

		loanEntity.setCustomer(customerEntity);

		entityManager.persist(loanEntity);
		
		return loanEntity.getLoanId();

	}

	@Override
	public void closeLoan(Integer loanId){
		
		LoanEntity loanEntity=entityManager.find(LoanEntity.class, loanId);
		
		loanEntity.setCustomer(null);
		
		
	}
	
	
	@Override
	public void deleteLoan(Integer loanId) throws Exception {
		// TODO Auto-generated method stub
		LoanEntity loanEntity=entityManager.find(LoanEntity.class, loanId);

		//If both loan and customer details needs to be deleted
//		entityManager.remove(loanEntity);

		//If only loan details needs to be deleted
		 loanEntity.setCustomer(null);
		 entityManager.remove(loanEntity);


	}



}