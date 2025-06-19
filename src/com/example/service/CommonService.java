package com.example.service;

import com.example.dao.AbstractDao;
import com.example.model.Booking;
import com.example.model.Customer;
import com.example.model.MasterData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;

public abstract  class CommonService<T extends MasterData> {

    public static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    public static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    private final AbstractDao<T> abstractDao;
    public abstract void prepareUpdateFields(T updateObject, T object);
    public String serviceName;

    public CommonService(AbstractDao<T> abstractDao, String serviceName){
        this.abstractDao = abstractDao;
        this.serviceName = serviceName;
    }

    public void create(T object){
        abstractDao.create(object);
    }

    public void update(int id, T object) {
        T updateObject = abstractDao.findById(id);
        prepareUpdateFields(updateObject, object);
        abstractDao.update(id,updateObject);
    }

    public int getId() {
        int id;
        try {
            System.out.print("Enter "+serviceName+" ID: ");
            id = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException | NumberFormatException | NullPointerException ex) {
            System.out.println("Please enter valid value");
            return getId();
        }
        return id;
    }

    public int findById() {
        int checkedId = 0;
        try {
            T object = abstractDao.findById(getId());
            checkedId = object.getId();
        } catch (NullPointerException ex) {
            System.out.print("\nNo "+serviceName+" found!Please enter valid ID\n");
            return findById();
        }
        return checkedId;
    }
    public void displayAll() {
        for (T object : abstractDao.getAll()) {
            System.out.println(object);
        }
    }
}
