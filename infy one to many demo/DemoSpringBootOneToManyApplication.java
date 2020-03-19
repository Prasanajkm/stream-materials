package com.infy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import com.infy.model.Card;
import com.infy.model.Customer;
import com.infy.service.CardCustomerService;

@SpringBootApplication
public class DemoSpringBootOneToManyApplication implements CommandLineRunner {

	@Autowired
	CardCustomerService cardCustomerService;

	@Autowired
	Environment environment;


	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootOneToManyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// addCustomer();
		// addNewCustomerWithNewCards();
		// addNewCardToExistingCustomer();
	// getCustomerWithCardDetails();
	//	 deleteCardOfExistingCustomer();
		 deleteCustomer();
	}

	public void getCustomerWithCardDetails() {

		try {

			Integer customerId = 1001;

			Customer customer = cardCustomerService
					.getCustomerDetails(customerId);
			System.out.println("******Customer Details*****");
			System.out.println("Customer ID :" + customer.getCustomerId());
			System.out.println("Name :" + customer.getName());
			System.out.println("Email ID :" + customer.getEmailId());
			System.out.println("******Card Details******");
			if (!customer.getCards().isEmpty()) {
				for (Card card : customer.getCards()) {
					System.out.println("Card ID :" + card.getCardId());
					System.out.println("Card Number:" + card.getCardNumber());
					System.out.println("Expiry Date :" + card.getExpiryDate()
							+ "\n");

				}
			} else {
				System.out.println(environment
						.getProperty("UserInterface.NO_CARDS"));
			}

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("\nERROR:" + message);
		}

	}

	public void addCustomer() {

		try {
			Customer customer = new Customer();

			customer.setName("Mathew Rosley");
			customer.setEmailId("mathew@infy.com");
			customer.setDateOfBirth(LocalDate.of(1992, 1, 10));

			Integer customerId = cardCustomerService.addCustomer(customer);

			System.out.println("\n"
					+ environment.getProperty("UserInterface.CUSTOMER_ADDED")
					+ customerId);

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("\nERROR:" + message);

		}

	}

	public void addNewCardToExistingCustomer() throws Exception {

		Integer customerId = 1004;

		Card cardDetails = new Card();
		cardDetails.setCardId(12355);
		cardDetails.setCardNumber("6642160685012167");
		cardDetails.setExpiryDate(LocalDate.of(2030, 03, 07));
		try {
			cardCustomerService.addNewCardToExistingCustomer(customerId,
					cardDetails);
			System.out.println("\n"
					+ environment.getProperty("UserInterface.CARD_ADDED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("\nERROR:" + message);
		}

	}

	public void addNewCustomerWithNewCards() {
		try {

			Customer customer = new Customer();
			customer.setName("Tom Rosley");
			customer.setEmailId("Tom@infy.com");
			customer.setDateOfBirth(LocalDate.of(1992, 1, 10));

			Card cardDetails1 = new Card();
			cardDetails1.setCardId(12353);
			cardDetails1.setCardNumber("3342160067012156");
			cardDetails1.setExpiryDate(LocalDate.of(2024, 02, 27));

			Card cardDetails2 = new Card();
			cardDetails2.setCardId(12354);
			cardDetails2.setCardNumber("2342112231701215");
			cardDetails2.setExpiryDate(LocalDate.of(2022, 10, 15));

			Set<Card> cards = new LinkedHashSet<>();
			cards.add(cardDetails1);
			cards.add(cardDetails2);

			customer.setCards(cards);

			cardCustomerService.addNewCustomerWithNewCard(customer);
			System.out
					.println("\n"
							+ environment
									.getProperty("UserInterface.CARD_AND_CUSTOMER_ADDED"));

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("\nERROR:" + message);

		}

	}

	public void deleteCardOfExistingCustomer() {
		try {

			Integer customerId = 1001;

			List<Integer> cardIdsToDelete = new ArrayList<>();
			cardIdsToDelete.add(12346);
			cardIdsToDelete.add(12347);

			cardCustomerService.deleteCardOfExistingCustomer(customerId,
					cardIdsToDelete);
			System.out
					.println("\n"
							+ environment
									.getProperty("UserInterface.CARD_DEACTIVATED"));

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			e.printStackTrace();
			System.out.println("\nERROR:" + message);

		}

	}

	public void deleteCustomer() {
		try {

			Integer customerId = 1001;

			cardCustomerService.deleteCustomer(customerId);
			System.out
					.println("\n"
							+ environment
									.getProperty("UserInterface.CUSTOMER_DELETED"));

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("\nERROR:" + message);
		}

	}

}
