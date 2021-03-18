package org.sg.dao.impl;

import org.sg.dao.AddressDao;
import org.sg.dao.GenericDao;
import org.sg.entities.AddressEntity;

public class AddressDaoImpl extends GenericDao implements AddressDao {

	public void delete(AddressEntity addressEntity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(addressEntity));
		entityManager.getTransaction().commit();
	}
}
