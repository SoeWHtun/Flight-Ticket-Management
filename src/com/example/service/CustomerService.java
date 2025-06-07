package com.example.service;

import com.example.dao.CustomerDAO;
import com.example.model.Customer;
import com.example.util.FileUtil;

public class CustomerService {

	public static void create(Customer customer) {

		CustomerDAO.addCustomer(customer);
	}

	public static void update(int id, Customer customer) {
		Customer updateCustomer = CustomerDAO.findById(id);
		updateCustomer.setName(customer.getName());
		updateCustomer.setEmail(customer.getEmail());
		updateCustomer.setPhone(customer.getPhone());
//		FileUtil.csvUpdater("customer.csv",updateCustomer.toArray());
	}

}
