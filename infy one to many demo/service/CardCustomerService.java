package com.infy.service;

import java.util.List;

import com.infy.model.Card;
import com.infy.model.Customer;


public interface CardCustomerService {

	public Customer getCustomerDetails(Integer id) throws Exception;
	public Integer addCustomer(Customer customer) throws Exception;
	public void addNewCardToExistingCustomer(Integer id,Card card) throws Exception;
	public Integer addNewCustomerWithNewCard(Customer customer) throws Exception;
	public void deleteCustomer(Integer id) throws Exception;
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws Exception;	
	
}
