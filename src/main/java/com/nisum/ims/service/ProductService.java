package com.nisum.ims.service;

import java.util.List;

import com.nisum.ims.entity.Product;

public interface ProductService {

	List<Product> findProductById(Integer productId);

	Product findByProductName(String productName);

	void saveProduct(Product product);

	void updateProduct(Product product);

	void deleteProductById(Integer productId);

	List<Product> findAllProducts();

	public boolean isProductExist(Product product);

}
