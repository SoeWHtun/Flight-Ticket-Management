package com.example.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Customer;
import com.example.util.FileUtil;

public class CustomerDAO {
	private static Customer[] customerDB = new Customer[1000];
	private static int customerCount = 0;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	static {
		String[] header = {"Id", "Name", "Phone", "Email"};
		FileUtil.csvCreater("customer.csv",header);
	}

	public static int getId(){
		List<Customer> customerList = getAll();
		customerList.sort((c1, c2)-> Integer.compare(c1.getCustomerId(), c2.getCustomerId()));
		return customerList.getLast().getCustomerId() + 1;
	}

	public static int getCustomerCount() {
		return customerCount;
	}

	public static void addCustomer(Customer customer) {
		customerCount++;
		customer.setCustomerId(getId());
		FileUtil.csvWriter("customer.csv", customer.toArray());
	}

	public static Customer findById(int id) {
		for (Customer customer : getAll()) {
			if (customer.getCustomerId() == id) {
				return customer;
			}
		}
		return null;
	}

	public static List<Customer> getAll(){
		List<String[]> customersData = FileUtil.csvReader("customer.csv");
		List<Customer> customerList = toCustomers(customersData);
		return customerList;
	}

	private static List<Customer> toCustomers(List<String[]> customersData) {
		List<Customer> customerList = new ArrayList<>();
		for(String[] customerRow : customersData) {
			Customer customer = Customer.toObj(customerRow);
			customerList.add(customer);
		}
		return customerList;
	}

	public static void displayCustomer() {
		for (Customer customer : getAll()) {
			System.out.println(customer);
		}
	}

	public static void delete(int customerId) {
		for (int i = 0; i < customerCount; i++) {
			if (customerDB[i].getCustomerId() == customerId) {
				int j = i;
				while (customerCount > j) {
					customerDB[j] = customerDB[j + 1];
					j++;
				}
				customerCount--;
				return;

			}
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
			Customer checkCustomer = findById(id);
			checkedId = checkCustomer.getCustomerId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo User found!Please enter valid ID\n");
			return checkCustomerID(getCustomerID());
		}
		return checkedId;
	}

}
