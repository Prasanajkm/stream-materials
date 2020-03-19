package com.infy.service;

import com.infy.model.AdharCard;
import com.infy.model.Customer;


public interface CustomerDetailsService {

	public Customer getCustomerByCustomerId(Integer customerId) throws Exception;
	public Integer addCustomerAndAdhar(Customer customer)throws Exception ;
	public Integer addNewCustomer(Customer customer) throws Exception;
	public void addAdharToExistingCustomer(Integer id, AdharCard adharcard) throws Exception;
	public void deleteCustomer(Integer id) throws Exception;
	
}
