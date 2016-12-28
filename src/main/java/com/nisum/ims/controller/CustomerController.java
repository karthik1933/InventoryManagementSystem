package com.nisum.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nisum.ims.entity.Customer;
import com.nisum.ims.service.CustomerService;

@RestController
@RequestMapping(value = "/Customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	// -------------------Retrieve All Customers----------------------------------------------

		@RequestMapping(value = "/customerList", method = RequestMethod.GET)
		public ResponseEntity<List<Customer>> listAllCustomers() {
			List<Customer> customers = customerService.findAllCustomers();
			if (customers.isEmpty()) {
				return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);// You may decide to return HttpStatus.NOT_FOUND														
			}
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		}

		// -------------------Retrieve Single Customer--------------------------------------------

		@RequestMapping(value = "/customer/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public List<Customer> getCustomer(@PathVariable("code") String code) {
			List<Customer> customers = customerService.findByCustomerCode(code);
			return customers;
		}

		// -------------------Create a Customer--------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.POST)
		public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
			if (customerService.isCustomerExist(customer)) {
				System.out.println("A Customer with name " + customer.getCompanyName() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			customerService.saveCustomer(customer);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/customer/{code}").buildAndExpand(customer.getCode()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		// ------------------- Update a Customer-----------------------------------------------------

		@RequestMapping(value = "/update/{code}", method = RequestMethod.PUT)
		public void updateCustomer(@PathVariable("code") String code, @RequestBody Customer customer) {
			customerService.updateCustomer(customer);
		}

		// ------------------- Delete Customer--------------------------------------------------

		@RequestMapping(value = "/delete/{code}", method = RequestMethod.DELETE)
		public ResponseEntity<Customer> deleteCustomer(@PathVariable("code") String code) {
						List<Customer> customers = customerService.findByCustomerCode(code);
			if (customers == null) {
				System.out.println("Unable to delete customer with code " + code + " not found");
				return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
			}

			customerService.deleteCustomerByCode(code);
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
}
