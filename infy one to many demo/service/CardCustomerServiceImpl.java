package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.infy.dao.CardCustomerDAO;
import com.infy.model.Card;
import com.infy.model.Customer;

@Service(value = "cardCustomerService")
@Transactional
public class CardCustomerServiceImpl implements CardCustomerService {

	@Autowired
	private CardCustomerDAO cardCustomerDAO;

	// Fetch customer details
	@Override
	
	public Customer getCustomerDetails(Integer id) throws Exception {

		Customer customer = null;
		customer = cardCustomerDAO.getCustomerDetails(id);
		if (customer == null) {
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
		}
		return customer;
	}

	@Override
	public Integer addCustomer(Customer customer) throws Exception {

		Integer id = null;
		id = cardCustomerDAO.addCustomer(customer);
		return id;
	}

	@Override
	public void addNewCardToExistingCustomer(Integer id, Card card)
			throws Exception {

		if (cardCustomerDAO.getCustomerDetails(id) != null) {
			cardCustomerDAO.addNewCardToExistingCustomer(id, card);
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

	@Override
	public Integer addNewCustomerWithNewCard(Customer customer) throws Exception {

		Integer id = null;

		id = cardCustomerDAO.addNewCustomerWithNewCard(customer);

		return id;
	}

	@Override
	public void deleteCustomer(Integer id) throws Exception {
		if (cardCustomerDAO.getCustomerDetails(id) != null) {
			cardCustomerDAO.deleteCustomer(id);
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

	@Override
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws Exception {		
		if (cardCustomerDAO.getCustomerDetails(customerId) != null) {
			    Integer cardDeleteCount=cardCustomerDAO.deleteCardOfExistingCustomer(customerId, cardIdsToDelete);	
				if(cardDeleteCount == -1){
					throw new Exception("Service.CARD_DOESNOT_EXIST");
				}
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

}
