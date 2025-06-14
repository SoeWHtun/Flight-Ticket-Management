package com.example.dao.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.AbstractDao;
import com.example.model.Customer;
import com.example.util.FileUtil;

import static com.example.dao.customer.CustomerDao.FILE_NAME;

public class CustomerDaoImpl implements AbstractDao<Customer> {
    public static CustomerDaoImpl customerDao = new CustomerDaoImpl();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    static {
        String[] header = {"Id", "Name", "Phone", "Email"};
        FileUtil.csvCreater(FILE_NAME, header);
    }

    public void displayCustomer() {
        for (Customer customer : getAll()) {
            System.out.println(customer);
        }
    }

    public int getCustomerID() {
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

    public int checkCustomerID(int id) {
        int checkedId = 0;
        try {
            Customer checkCustomer = findById(id);
            checkedId = checkCustomer.getId();
        } catch (NullPointerException ex) {
            System.out.print("\nNo User found!Please enter valid ID\n");
            return checkCustomerID(getCustomerID());
        }
        return checkedId;
    }

    @Override
    public void create(Customer customer) {

        customer.setId(getCSVId());
        FileUtil.csvWriter(FILE_NAME, customer.toArray());
    }

    @Override
    public Customer findById(int id) {
        for (Customer customer : getAll()) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public void update(int id, Customer customer) {
        FileUtil.updateRecordById(FILE_NAME, id + "", customer.toArray());
    }

    @Override
    public void delete(int id) {
        FileUtil.deleteRecordById(FILE_NAME, id + "");
    }

    @Override
    public List<Customer> getAll() {
        List<String[]> customersData = FileUtil.csvReader(FILE_NAME);
        List<Customer> customerList = toObjects(customersData);
        return customerList;
    }
}
