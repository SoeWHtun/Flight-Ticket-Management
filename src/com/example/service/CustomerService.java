package com.example.service;

import com.example.dao.customer.CustomerDao;
import com.example.dao.customer.CustomerDaoImpl;
import com.example.model.Customer;

public class CustomerService extends CommonService<Customer> {
    private static final CustomerDao customerDaoImpl = new CustomerDaoImpl();

    public CustomerService() {
        super(customerDaoImpl, "Customer");
    }

    @Override
    public void prepareUpdateFields(Customer updateObject, Customer object) {
        updateObject.setName(object.getName());
        updateObject.setPhone(object.getPhone());
        updateObject.setEmail(object.getEmail());
    }
}
