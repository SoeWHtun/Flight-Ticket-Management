package com.example.service;

import com.example.model.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;

public class CustomerService {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static void create(Customer customer) {
		customerDao.create(customer);
	}

	public static void update(int id, Customer customer) {
		Customer updateCustomer = customerDao.findById(id);
		updateCustomer.setName(customer.getName());
		updateCustomer.setEmail(customer.getEmail());
		updateCustomer.setPhone(customer.getPhone());
		customerDao.update(id, updateCustomer);
	}
	public static void displayCustomer() {
		for (Customer customer : customerDao.getAll()) {
			System.out.println(customer);
		}
	}

	public static int getCustomerID() {
		int customerID;
		try {
			System.out.print("Enter your customer ID: ");
			customerID = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			System.out.println("Please enter valid value");
			return getCustomerID();
		} catch (NumberFormatException ex) {
			System.out.println("Please enter valid ID");
			return getCustomerID();
		}
		return customerID;
	}

	public static int checkCustomerID(int id) {
		int checkedId = 0;
		try {
			Customer checkCustomer = customerDao.findById(id);
			checkedId = checkCustomer.getId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo User found!Please enter valid ID\n");
			return checkCustomerID(getCustomerID());
		}
		return checkedId;
	}


}
