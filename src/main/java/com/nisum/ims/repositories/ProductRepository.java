package com.nisum.ims.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.ims.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer> {

}
