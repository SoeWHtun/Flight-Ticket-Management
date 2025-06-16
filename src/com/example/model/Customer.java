package com.example.model;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;

public class Customer extends MasterData{
    private String name;
    private String phone;
    private  String email;

	public Customer(){

	}
    
    public Customer(String name,String phone,String email) {
    	super.setId(customerDao.getCount()+1);
    	this.name=name;
    	this.phone=phone;
    	this.email=email;
    }

	public Customer(int id, String name,String phone,String email) {
		super.setId(id);
		this.name=name;
		this.phone=phone;
		this.email=email;
	}

	public static Customer toObj(String[] customerRow) {
		int id = Integer.parseInt(customerRow[0]);
		String name = customerRow[1];
		String phone = customerRow[2];
		String email = customerRow[3];
		return new Customer(id,name,phone,email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] toArray(){
		return new String[]{ getId()+"",this.name, this.phone, this.email};
	}
	
	@Override
	public String toString() {
		String str = "\nCustomer ID: " + getId();
		str += "\nCustomer Name: " + getName();
		str += "\nPhone Number: " + getPhone();
		str += "\nEmail: " + getEmail()+"\n";
		
		return str;
	}
}
