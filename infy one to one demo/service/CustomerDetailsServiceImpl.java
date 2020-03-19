package com.infy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.CustomerDetailsDAO;
import com.infy.model.AdharCard;
import com.infy.model.Customer;

@Service(value = "customerDetailsService")
@Transactional
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	@Autowired
	private CustomerDetailsDAO customerDetailsDAO;

	@Override
	public Customer getCustomerByCustomerId(Integer customerId)
			throws Exception {

		Customer customer = null;
		customer = customerDetailsDAO.getCustomerByCustomerId(customerId);
		if (customer == null) {
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
		}
		return customer;
	}

	@Override
	@Transactional
	public Integer addCustomerAndAdhar(Customer customer) throws Exception {

		Integer id = null;

		id = customerDetailsDAO.addCustomerAndAdhar(customer);

		return id;
	}

	@Override
	@Transactional
	public Integer addNewCustomer(Customer customer) throws Exception {

		Integer id = null;
		
		id = customerDetailsDAO.addNewCustomer(customer);

		return id;
	}

	@Override
	@Transactional
	public void addAdharToExistingCustomer(Integer id, AdharCard adharCard)
			throws Exception {
		if (customerDetailsDAO.getCustomerByCustomerId(id) != null) {
			if(customerDetailsDAO.getCustomerByCustomerId(id).getAdharcard()==null)
			customerDetailsDAO.addAdharToExistingCustomer(id, adharCard);
			else
				throw new Exception("DAO.ADHARCARD_EXISTS");
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}


	@Override
	@Transactional
	public void deleteCustomer(Integer id) throws Exception {
		if (customerDetailsDAO.getCustomerByCustomerId(id) != null) {
			customerDetailsDAO.deleteCustomer(id);

		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
		
	}

}
