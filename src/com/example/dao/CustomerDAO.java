package com.example.dao;

import com.example.model.Customer;

public class CustomerDAO {

	private static Customer[] customerDB = new Customer[1000];
	private static int customerCount = 0;

	static {
		addCustomer(new Customer("U Kan Hla", "09345455", "kanhla3454@gmail.comss"));
		addCustomer(new Customer("Daw Mya Mya", "09451234", "myamya@gmail.com"));
		addCustomer(new Customer("Ko Aung Min", "09234567", "aungmin@yahoo.com"));
		addCustomer(new Customer("Ma Su Su", "09567890", "susu@gmail.com"));
		addCustomer(new Customer("U Tin Tun", "09112233", "tintun@hotmail.com"));
		addCustomer(new Customer("Daw Khin Khin", "09654321", "khinkhin@mail.com"));
		addCustomer(new Customer("Ko Zaw Zaw", "09778990", "zawzaw@gmail.com"));
		addCustomer(new Customer("Ma Moe Moe", "09812345", "moemoe@gmail.com"));
		addCustomer(new Customer("U Hla Hla", "09336677", "hlahla@gmail.com"));
		addCustomer(new Customer("Daw Yin Yin", "09447788", "yinyin@mail.com"));
	}

	public static int getCustomerCount() {
		return customerCount;
	}

	public static void addCustomer(Customer customer) {
		customerCount++;
		customerDB[customerCount - 1] = customer;

	}

	public static Customer findById(int id) {
		for (int i = 0; i < customerCount; i++) {
			if (customerDB[i].getCustomerId() == id) {
				return customerDB[i];
			}
		}

		return null;
	}

	public static void displayCustomer() {
		for (int i = 0; i < customerCount; i++) {
			System.out.println(customerDB[i]);
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

}
