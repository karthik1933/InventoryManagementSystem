package com.nisum.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.ims.entity.Product;
import com.nisum.ims.repositories.ProductRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	// private static List<Product> products=productRepository.findAll();

	@Override
	public List<Product> findProductById(String productId) {

		return productRepository.findAll();
	}

	@Override
	public Product findByProductName(String productName) {
		/*
		 * for (Product product : products) { if
		 * (product.getProductName().equalsIgnoreCase(productName)) { return
		 * product; } }
		 */
		productRepository.findAll();
		return null;
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);

	}

	@Override
	public void deleteProductById(String productId) {
		productRepository.delete(productId);

	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	@Override
	public boolean isProductExist(Product product) {
		return findByProductName(product.getProductName()) != null;
	}
}
