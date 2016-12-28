package com.nisum.ims.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.ims.entity.Supplier;

public interface SupplierRepository extends MongoRepository<Supplier, String> {

}
