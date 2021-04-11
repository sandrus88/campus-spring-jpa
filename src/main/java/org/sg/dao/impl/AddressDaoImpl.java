package org.sg.dao.impl;

import java.util.List;

import org.sg.dao.AddressDao;
import org.sg.dao.GenericDao;
import org.sg.entities.AddressEntity;
import org.sg.entities.CourseEntity;

public class AddressDaoImpl extends GenericDao implements AddressDao {

	@Override
	public AddressEntity insert(AddressEntity addressEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(addressEntity);
		entityManager.getTransaction().commit();
		return addressEntity;
	}

	@Override
	public AddressEntity get(AddressEntity addressEntity) {
		return addressEntity;
	}

	@Override
	public AddressEntity update(AddressEntity addressEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(addressEntity);
		entityManager.getTransaction().commit();
		return addressEntity;
	}
	
	public void delete(AddressEntity addressEntity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(addressEntity));
		entityManager.getTransaction().commit();
	}

	@Override
	public List<AddressEntity> getAll() {
		entityManager.getTransaction().begin();
		List<AddressEntity> addresses = entityManager.createQuery("from AddressEntity", AddressEntity.class).getResultList();
		entityManager.getTransaction().commit();
		return addresses;
	}
}
