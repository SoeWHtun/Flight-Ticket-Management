package com.example.dao.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




import com.example.dao.booking.BookingDao;
import com.example.model.Customer;
import com.example.util.FileUtil;


public class CustomerDaoImpl implements CustomerDao {
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
    public Customer toObj(String[] row) {
        return Customer.toObj(row);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
