package org.sg.dao.impl;

import org.sg.dao.AddressDao;
import org.sg.dao.GenericDao;
import org.sg.entities.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends GenericDao implements AddressDao {

	@Override
	public AddressEntity insert(AddressEntity addressEntity) {
		entityManager.persist(addressEntity);
		return addressEntity;
	}

	@Override
	public AddressEntity get(AddressEntity addressEntity) {
		return addressEntity;
	}

	@Override
	public AddressEntity update(AddressEntity addressEntity) {
		entityManager.merge(addressEntity);
		return addressEntity;
	}
	
	public void delete(AddressEntity addressEntity) {
		entityManager.remove(entityManager.merge(addressEntity));
	}
}
