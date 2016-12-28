package com.nisum.ims.service;

import java.util.List;

import com.nisum.ims.entity.Customer;

public interface CustomerService {

	List<Customer> findByCustomerCode(String code);

	Customer findByCompanyName(String companyName);

	void saveCustomer(Customer Customer);

	void updateCustomer(Customer Customer);

	void deleteCustomerByCode(String code);

	List<Customer> findAllCustomers();

	public boolean isCustomerExist(Customer Customer);

}
