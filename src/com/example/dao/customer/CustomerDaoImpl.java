package com.example.dao.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.example.model.Customer;
import com.example.util.FileUtil;


public class CustomerDaoImpl implements CustomerDao {
    public static CustomerDaoImpl customerDao = new CustomerDaoImpl();


    static {
        String[] header = {"Id", "Name", "Phone", "Email"};
        FileUtil.csvCreater(FILE_NAME, header);
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
