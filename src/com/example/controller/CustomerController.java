package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.model.Customer;
import com.example.service.CustomerService;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;

public class CustomerController {

	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	public void call() throws NumberFormatException, IOException {
		int choice;
		do {

			System.out.println("\n**Customer Management**");

			System.out.println("1. Customer Registration ");
			System.out.println("2. Update Customer detail ");
			System.out.println("3. Find Customer By Id");
			System.out.println("4. View All Customer");
			System.out.println("5. Delete Customer");
			System.out.println("6. Exit");
			choice = FlightTicketManagement.getChoice();

			switch (choice) {
			case 1 -> create();
			case 2 -> edit();
			case 3 -> findById();
			case 4 -> viewAll();
			case 5 -> delete();
			case 6 -> System.out.println("Exited");
			default -> System.out.println("Invalid choice! Please select a valid option.");
			}

		} while (choice != 6);
		System.out.println();
	}

	private void delete() throws IOException {
		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		customerDao.delete(cCustomerId);
		customerDao.displayCustomer();
	}

	private void viewAll() {
		customerDao.displayCustomer();
	}

	private void findById() throws IOException {
		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		Customer foundCustomer = customerDao.findById(cCustomerId);
		System.out.println(foundCustomer);
	}

	private void edit() throws IOException {
		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		System.out.print("Enter customer name: ");
		String name = bufferedReader.readLine();
		System.out.print("Enter customer phone: ");
		String phone = bufferedReader.readLine();
		System.out.print("Enter customer email: ");
		String email = bufferedReader.readLine();
		Customer updateCustomer = new Customer(name, phone, email);
		CustomerService.update(cCustomerId, updateCustomer);
		System.out.println("\nCustomer Details Updated\n");
		Customer foundCustomer = customerDao.findById(cCustomerId);
		System.out.println(foundCustomer);
	}

	private void create() throws IOException {
		System.out.print("Enter customer name: ");
		String name = bufferedReader.readLine();
		System.out.print("Enter customer phone: ");
		String phone = bufferedReader.readLine();
		System.out.print("Enter customer email: ");
		String email = bufferedReader.readLine();
		Customer newCustomer = new Customer(name, phone, email);
		CustomerService.create(newCustomer);
		System.out.println("New Customer Created\n");
		System.out.println(newCustomer);
	}

}
