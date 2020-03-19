package com.infy.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.infy.entity.CardEntity;
import com.infy.entity.CustomerEntity;
import com.infy.model.Card;
import com.infy.model.Customer;

@Repository(value = "cardCustomerDao")
public class CardCustomerDAOImpl implements CardCustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;

	// fetches the customer details of a particular customer
	@Override
	public Customer getCustomerDetails(Integer id) throws Exception {
		Customer customer = null;
		Set<Card> cardDetails = new LinkedHashSet<>();

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, id);
		System.out.println(customerEntity);
		if (customerEntity != null) {
			customer = new Customer();
			customer.setEmailId(customerEntity.getEmailId());
			customer.setName(customerEntity.getName());
			customer.setCustomerId(customerEntity.getCustomerId());
			customer.setDateOfBirth(customerEntity.getDateOfBirth());
			Set<CardEntity> cardEntities = customerEntity.getCardEntities();
			if (!cardEntities.isEmpty()) {
				for (CardEntity cardEntity : cardEntities) {
					Card card = new Card();
					card.setCardId(cardEntity.getCardId());
					card.setCardNumber(cardEntity.getCardNumber());
					card.setExpiryDate(cardEntity.getExpiryDate());
					cardDetails.add(card);
				}
			}
			customer.setCards(cardDetails);
		}
		return customer;
}

	// Adds a new customer with all the details
	@Override
	public Integer addCustomer(Customer customer) {
		Integer customerId = null;
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(customer.getName());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());
		customerEntity.setCardEntities(null);
		entityManager.persist(customerEntity);
		customerId = customerEntity.getCustomerId();
		return customerId;
	}

	// adds a new credit card to a new customer
	@Override
	public Integer addNewCustomerWithNewCard(Customer customer) {
		Integer customerId = null;
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(customer.getName());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());
		Set<Card> cardsToAllocate = customer.getCards();
		Set<CardEntity> cards = new LinkedHashSet<>();
		for (Card card : cardsToAllocate) {
			CardEntity newCard = new CardEntity();
			newCard.setCardId(card.getCardId());
			newCard.setCardNumber(card.getCardNumber());
			newCard.setExpiryDate(card.getExpiryDate());
			cards.add(newCard);
		}
		customerEntity.setCardEntities(cards);
		entityManager.persist(customerEntity);
		customerId = customerEntity.getCustomerId();
		return customerId;
	}

	// adds a new card to an existing customer
	@Override
	public void addNewCardToExistingCustomer(Integer id, Card card) {

		CustomerEntity customer = entityManager.find(CustomerEntity.class, id);

		CardEntity cardEntity = new CardEntity();
		cardEntity.setCardId(card.getCardId());
		cardEntity.setCardNumber(card.getCardNumber());
		cardEntity.setExpiryDate(card.getExpiryDate());
		customer.getCardEntities().add(cardEntity);
		//entityManager.persist(customer);

	}

	// deletes an customer
	// along with the cards associated with him
	// and delete those cards from card table as well
	@Override
	public void deleteCustomer(Integer id) {
		CustomerEntity customer = entityManager.find(CustomerEntity.class, id);
		entityManager.remove(customer);
	}

	// Deallocates particular cards of an existing customer
	// deletes the card from the card table as well
	// returns -1 if a card do not belong to a customer
	// returns count of cards deleted if all the cards, to be deleted, belongs
	// to customer

	@Override
	public Integer deleteCardOfExistingCustomer(Integer customerId,
			List<Integer> cardIdsToDelete) {
		Integer deletedCardCount = 0;
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerId);
		for (Integer cardId : cardIdsToDelete) {
			CardEntity cardEntity = entityManager.find(CardEntity.class, cardId);

			if (cardEntity != null) {
				customerEntity.getCardEntities().remove(cardEntity);
				entityManager.remove(cardEntity);
				deletedCardCount++;
			} else {
				return -1;
			}
		}
		return deletedCardCount;
	}

}
