package com.infy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.model.Customer;
import com.infy.model.Loan;
import com.infy.service.CustomerLoanService;

@SpringBootApplication
public class DemoSpringBootManyToOneApplication implements CommandLineRunner {

	@Autowired
	CustomerLoanService service;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootManyToOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//getLoanDetails();
		//addLoanDetails();
		//addLoanAndCustomer();
		//allocateNewLoanToExistingCustomer();
		//deleteLoan();
		//closeLoan();

	}

	public void getLoanDetails() {
		// TODO Auto-generated method stub

		try {
			Loan loan=service.getLoanDetails(2001);
			System.out.println("Loan Details");
			System.out.println("---------------------------");

			System.out.println("Loan Id: "+loan.getLoanId());
			System.out.println("Loan Amount: "+loan.getAmount());
			System.out.println("Loand Issue Date: "+loan.getLoanIssueDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));

			System.out.println("Customer Details");
			System.out.println("---------------------");

			System.out.println("Customer Id: "+loan.getCustomer().getCustomerId());
			System.out.println("Email Id: "+loan.getCustomer().getEmailId());




		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println("\nERROR:" + message);
		}





	}

	public void addLoanDetails() {
		// TODO Auto-generated method stub
		try{
			Loan loan=new Loan();
			loan.setAmount(556279.0);
			loan.setLoanIssueDate(LocalDate.of(2015, 11, 1));

			Integer loanId=service.addLoanDetails(loan);
			System.out.println(environment.getProperty("UserInterface.NEW_LOAN_SUCCESS")+loanId);
		}catch(Exception e){
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println("\nERROR:" + message);
		}
	}

	public void addLoanAndCustomer() {
		try{
			Loan loan=new Loan();
			loan.setAmount(556279.0);
			loan.setLoanIssueDate(LocalDate.of(2015, 11, 1));


			Customer customer=new Customer();
			customer.setCustomerId(1006);
			customer.setDateOfBirth(LocalDate.of(1992, 1, 10));
			customer.setEmailId("peter@infy.com");
			customer.setName("Peter");

			loan.setCustomer(customer);

			Integer loanId=service.addLoanAndCustomer(loan);
			System.out.println(environment.getProperty("UserInterface.NEW_LOAN_CUSTOMER_SUCCESS")+loanId);


		}catch(Exception e){
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println("\nERROR:" + message);
		}

	}

	public void allocateNewLoanToExistingCustomer() {
		// TODO Auto-generated method stub
		try{
			
			Loan loan=new Loan();
			loan.setAmount(573279.0);
			loan.setLoanIssueDate(LocalDate.of(2013, 11, 1));
			
			Integer customerId=1005;
			service.allocateNewLoanToExistingCustomer(customerId, loan);
			System.out.println(environment.getProperty("UserInterface.LOAN_UPDATE_SUCCESS"));

		}catch(Exception e){
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println("\nERROR:" + message);
		}

	}

	public void deleteLoan() {
		// TODO Auto-generated method stub
		try {
			Integer loanId=2003;
			service.deleteLoan(loanId);
			System.out.println(environment.getProperty("UserInterface.LOAN_DELETE_SUCCESS"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println("\nERROR:" + message);
		}

	}
	
	public void closeLoan() {
		// TODO Auto-generated method stub
		try {
			Integer loanId=2003;
			service.closeLoan(loanId);
			System.out.println(environment.getProperty("UserInterface.LOAN_CLOSE_SUCCESS"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println("\nERROR:" + message);
		}

	}

}

