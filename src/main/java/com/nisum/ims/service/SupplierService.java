package com.nisum.ims.service;

import java.util.List;

import com.nisum.ims.entity.Supplier;

public interface SupplierService {
	
	List<Supplier> findBySupplierCode(String code);

	Supplier findByCompanyName(String companyName);

	void saveSupplier(Supplier supplier);

	void updateSupplier(Supplier supplier);

	void deleteSupplierByCode(String code);

	List<Supplier> findAllSuppliers();

	public boolean isSupplierExist(Supplier supplier);

}
