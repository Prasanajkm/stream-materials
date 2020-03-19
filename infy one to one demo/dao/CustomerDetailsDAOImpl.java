package com.infy.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infy.entity.AdharCardEntity;
import com.infy.entity.CustomerEntity;
import com.infy.model.AdharCard;
import com.infy.model.Customer;

@Repository(value = "customerDao")
public class CustomerDetailsDAOImpl implements CustomerDetailsDAO {
	
	

	@PersistenceContext
	private EntityManager entityManager;
	

	// Persisting source and target entity instances together:
	// Adding a new customer and a new adhar card and mapping this adhar card to
	// the customer.
	@Override
	public Integer addCustomerAndAdhar(Customer customer) {
		Integer id = null;

		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(customer.getName());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());

		AdharCardEntity adharEntity = new AdharCardEntity();
		adharEntity.setAdharCardNumber(customer.getAdharcard()
				.getAdharCardNumber());
		adharEntity.setAddress(customer.getAdharcard().getAddress());
		adharEntity.setPhoneNumber(customer.getAdharcard().getPhoneNumber());

		customerEntity.setAdharCard(adharEntity);

		entityManager.persist(customerEntity);

		id= customerEntity.getCustomerId();
		
		return id;

	}

	// Persisting only source entity instance:
	// Adding a new customer without any adhar card details.
	@Override
	public Integer addNewCustomer(Customer customer) {
		Integer id = null;


		CustomerEntity customerEntity = new CustomerEntity();

		customerEntity.setName(customer.getName());

		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());

		// If adhar card details are not set, automatically it will be set to
		// null
		customerEntity.setAdharCard(null); // optional

		entityManager.persist(customerEntity);
		
		id=customerEntity.getCustomerId();

		return id;
	}

	// Associating an existing source entity instance
	// with a new target entity instance:
	// For an existing customer assigning a new adhar card
	// (Only if customer doesn't have an adhar card assigned already).
	@Override
	public void addAdharToExistingCustomer(Integer id, AdharCard adharcard) {

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, id);

		AdharCardEntity adharCardEntity = new AdharCardEntity();

		adharCardEntity.setAddress(adharcard.getAddress());
		adharCardEntity.setAdharCardNumber(adharcard.getAdharCardNumber());
		adharCardEntity.setPhoneNumber(adharcard.getPhoneNumber());

		customerEntity.setAdharCard(adharCardEntity);

		entityManager.persist(customerEntity);

	}

	// Fetching source and target entity instances together:
	// Retrieving the details of a customer
	// along with the corresponding adhar card details if available
	@Override
	public Customer getCustomerByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub

		Customer customer = null;
		AdharCard adhar = null;



		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class,
				customerId);

		if (customerEntity != null) {
			customer = new Customer();

			customer.setCustomerId(customerEntity.getCustomerId());
			customer.setEmailId(customerEntity.getEmailId());
			customer.setName(customerEntity.getName());
			customer.setDateOfBirth(customerEntity.getDateOfBirth());

			if (customerEntity.getAdharCard() != null) {

				adhar = new AdharCard();
				adhar.setAddress(customerEntity.getAdharCard().getAddress());
				adhar.setAdharCardNumber(customerEntity.getAdharCard()
						.getAdharCardNumber());
				adhar.setPhoneNumber(customerEntity.getAdharCard()
						.getPhoneNumber());

				customer.setAdharcard(adhar);
			}

		}

		return customer;

	}

	// Removing the source entity
	// must remove corresponding target entity as well
	@Override
	public void deleteCustomer(Integer id) {
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, id);

		entityManager.remove(customerEntity);

	}

}