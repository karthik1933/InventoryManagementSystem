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

import com.nisum.ims.entity.Product;
import com.nisum.ims.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService productService;

	// -------------------Retrieve All
	// Products----------------------------------------------

	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> products = productService.findAllProducts();
		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);// You
																			// may
																			// decide
																			// to
																			// return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Product--------------------------------------------

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Product> getProduct(@PathVariable("id") String id) {
		List<Product> products = productService.findProductById(id);
		return products;
	}

	// -------------------Create a
	// Product--------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createProduct(@RequestBody Product product,UriComponentsBuilder ucBuilder){
		if (productService.isProductExist(product)) {
			System.out.println("A Product with name " + product.getProductName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		productService.saveProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getProductId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a
	// Product-----------------------------------------------------

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public void updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		productService.updateProduct(product);
	}

	// ------------------- Delete a Single
	// Product--------------------------------------------------

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
		List<Product> products = productService.findProductById(id);
		if (products == null) {
			System.out.println("Unable to delete product with id " + id + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		productService.deleteProductById(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
}