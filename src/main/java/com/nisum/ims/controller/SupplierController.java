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

import com.nisum.ims.entity.Supplier;
import com.nisum.ims.service.SupplierService;

@RestController
@RequestMapping(value = "/Supplier")
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	
	// -------------------Retrieve All Suppliers----------------------------------------------

		@RequestMapping(value = "/supplierList", method = RequestMethod.GET)
		public ResponseEntity<List<Supplier>> listAllSuppliers() {
			List<Supplier> suppliers = supplierService.findAllSuppliers();
			if (suppliers.isEmpty()) {
				return new ResponseEntity<List<Supplier>>(HttpStatus.NO_CONTENT);// You may decide to return HttpStatus.NOT_FOUND														
			}
			return new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
		}

		// -------------------Retrieve Single Supplier--------------------------------------------

		@RequestMapping(value = "/supplier/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody 
		public List<Supplier> getSupplier(@PathVariable("code") String code) {
			List<Supplier> suppliers = supplierService.findBySupplierCode(code);
			return suppliers;
		}

		// -------------------Create a Supplier--------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.POST)
		public ResponseEntity<Void> createSupplier(@RequestBody Supplier supplier, UriComponentsBuilder ucBuilder) {
			System.out.println("Creating Supplier " + supplier.getCompanyName());

			if (supplierService.isSupplierExist(supplier)) {
				System.out.println("A Supplier with name " + supplier.getCompanyName() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			supplierService.saveSupplier(supplier);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/supplier/{code}").buildAndExpand(supplier.getCode()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		// ------------------- Update a Supplier-----------------------------------------------------

		@RequestMapping(value = "/update/{code}", method = RequestMethod.PUT)
		public void updateCustomer(@PathVariable("code") String code, @RequestBody Supplier supplier) {
			supplierService.updateSupplier(supplier);
		}

		// ------------------- Delete Supplier--------------------------------------------------

		@RequestMapping(value = "/delete/{code}", method = RequestMethod.DELETE)
		public ResponseEntity<Supplier> deleteCustomer(@PathVariable("code") String code) {
			List<Supplier> suppliers = supplierService.findBySupplierCode(code);
			if (suppliers == null) {
				System.out.println("Unable to delete supplier with code " + code + " not found");
				return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
			}

			supplierService.deleteSupplierByCode(code);
			return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
		}
}
