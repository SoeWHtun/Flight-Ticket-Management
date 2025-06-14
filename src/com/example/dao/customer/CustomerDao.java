package com.example.dao.customer;

import com.example.dao.AbstractDao;
import com.example.model.Customer;

public interface CustomerDao extends AbstractDao<Customer> {
    public static final String FILE_NAME="customer.csv";
}
