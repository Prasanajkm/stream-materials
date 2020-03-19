package com.infy;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.model.AdharCard;
import com.infy.model.Customer;
import com.infy.service.CustomerDetailsService;

@SpringBootApplication
public class DemoSpringBootOneToOneApplication implements CommandLineRunner {

	@Autowired
	CustomerDetailsService customerDetailsService;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootOneToOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// addCustomerAndAdhar();
	        getCustomer();
		// addCustomer();
		// addAdharToExistingCustomer();
		// deleteCustomer();

	}

	public void getCustomer() {

		try {

			Customer customer = new Customer();
			customer.setCustomerId(1006);

			customer = customerDetailsService.getCustomerByCustomerId(customer
					.getCustomerId());

			System.out.println("*****Customer Details*****");
			System.out.println("Cust ID: " + customer.getCustomerId());
			System.out.println("Name: " + customer.getName());
			System.out.println("Emailid: " + customer.getEmailId());
			System.out.println("DOB: " + customer.getDateOfBirth());

			System.out.println("Adhar card Number: "
					+ customer.getAdharcard().getAdharCardNumber());
			System.out.println("Address: "
					+ customer.getAdharcard().getAddress());
			System.out.println("Phone Number: "
					+ customer.getAdharcard().getPhoneNumber());

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("\nERROR:" + message);
		}
	}

	public void addCustomerAndAdhar() {
		try {

			Customer customer = new Customer();

			customer.setName("Ron");
			customer.setEmailId("ron@infy.com");
			customer.setDateOfBirth(LocalDate.of(1993, 03, 24));

			AdharCard adharCard = new AdharCard();
			adharCard.setAdharCardNumber(234567614323l);
			adharCard.setAddress("Mysore, Karnataka");
			adharCard.setPhoneNumber("7654653566");

			customer.setAdharcard(adharCard);

			Integer id = customerDetailsService.addCustomerAndAdhar(customer);
			System.out
					.println("\n"
							+ environment
									.getProperty("UserInterface.CARD_AND_CUSTOMER_ADDED")
							+ id);

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
			customer.setName("Sinn");

			customer.setEmailId("sinn1@infy.com");
			customer.setDateOfBirth(LocalDate.of(1994, 06, 22));

			Integer id = customerDetailsService.addNewCustomer(customer);
			System.out.println("\n"
					+ environment.getProperty("UserInterface.CUSTOMER_ADDED")
					+ id);

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("\nERROR:" + message);

		}

	}

	public void addAdharToExistingCustomer() {

		try {
			Customer customer = new Customer();
			customer.setCustomerId(1007);
			AdharCard adharCard = new AdharCard();
			adharCard.setAdharCardNumber(344566754324L);
			adharCard.setAddress("Chandigarh, Punjab");
			adharCard.setPhoneNumber("9854463565");

			customerDetailsService.addAdharToExistingCustomer(
					customer.getCustomerId(), adharCard);
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

	public void deleteCustomer() {

		try {

			Customer customer = new Customer();
			customer.setCustomerId(1007);

			customerDetailsService.deleteCustomer(customer.getCustomerId());
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
