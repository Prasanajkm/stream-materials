package com.infy.dao;

import java.util.List;

import com.infy.model.Card;
import com.infy.model.Customer;

public interface CardCustomerDAO {

	public Customer getCustomerDetails(Integer id) throws Exception;
	public Integer addCustomer(Customer customer);
	public void addNewCardToExistingCustomer(Integer id, Card card);
	public Integer addNewCustomerWithNewCard(Customer customer) ;
	public void deleteCustomer(Integer id);
	public Integer deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete);	
	
	
		
	
		
	
}
