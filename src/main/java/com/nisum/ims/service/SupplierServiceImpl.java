package com.nisum.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.ims.entity.Supplier;
import com.nisum.ims.repositories.SupplierRepository;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<Supplier> findBySupplierCode(String code) {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier findByCompanyName(String companyName) {
		supplierRepository.findAll();
		return null;
	}

	@Override
	public void saveSupplier(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	@Override
	public void updateSupplier(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplierByCode(String code) {
		supplierRepository.delete(code);
	}

	@Override
	public List<Supplier> findAllSuppliers() {
		List<Supplier> suppliers = supplierRepository.findAll();
		return suppliers;
	}

	@Override
	public boolean isSupplierExist(Supplier supplier) {
		return findByCompanyName(supplier.getCompanyName()) != null;
	}

}
