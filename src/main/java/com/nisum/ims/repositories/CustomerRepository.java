package com.nisum.ims.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.ims.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
