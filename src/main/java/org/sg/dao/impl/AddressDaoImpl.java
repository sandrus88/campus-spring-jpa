package org.sg.dao.impl;

import org.sg.dao.AddressDao;
import org.sg.dao.GenericDao;
import org.sg.entities.AddressEntity;

public class AddressDaoImpl extends GenericDao implements AddressDao{

	public AddressEntity insert(AddressEntity addressEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(addressEntity);
		entityManager.getTransaction().commit();
		return addressEntity;
	}
	
	public AddressEntity update(AddressEntity addressEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entityManager.merge(addressEntity));
		entityManager.getTransaction().commit();
		return addressEntity;
	}

	public void delete(AddressEntity addressEntity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(addressEntity));
		entityManager.getTransaction().commit();	
	}
}
