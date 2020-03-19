package com.infy.dao;

import com.infy.model.AdharCard;
import com.infy.model.Customer;




public interface CustomerDetailsDAO {
	public Customer getCustomerByCustomerId(Integer customerId);
	public Integer addCustomerAndAdhar(Customer customer);
	public Integer addNewCustomer(Customer customer);
	public void addAdharToExistingCustomer(Integer id, AdharCard adharcard) throws Exception;
	public void deleteCustomer(Integer id);
}
