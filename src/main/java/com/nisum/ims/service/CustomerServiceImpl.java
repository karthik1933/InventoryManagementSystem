package com.nisum.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.ims.entity.Customer;
import com.nisum.ims.repositories.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findByCustomerCode(String code) {
		return customerRepository.findAll();
	}

	@Override
	public Customer findByCompanyName(String companyName) {
		customerRepository.findAll();
		return null;
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerByCode(String code) {
		customerRepository.delete(code);
	}

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public boolean isCustomerExist(Customer customer) {
		return findByCompanyName(customer.getCompanyName()) != null;
	}

}
