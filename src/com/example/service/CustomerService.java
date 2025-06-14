package com.example.service;

import com.example.model.Customer;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;

public class CustomerService {

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

}
