package org.sg.dao.impl;

import org.sg.dao.AddressDao;
import org.sg.dao.CustomHibernateDaoSupport;
import org.sg.entities.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends CustomHibernateDaoSupport implements AddressDao {

	@Override
	public AddressEntity insert(AddressEntity addressEntity) {
//		entityManager.getTransaction().begin();
//		entityManager.persist(addressEntity);
//		entityManager.getTransaction().commit();
//		return addressEntity;
		getHibernateTemplate().save(addressEntity);
		return addressEntity;
	}

	@Override
	public AddressEntity get(AddressEntity addressEntity) {
//		return addressEntity;
		addressEntity = getHibernateTemplate().get(AddressEntity.class, addressEntity.getId());
		return addressEntity;
	}

	@Override
	public AddressEntity update(AddressEntity addressEntity) {
//		entityManager.getTransaction().begin();
//		entityManager.persist(addressEntity);
//		entityManager.getTransaction().commit();
//		return addressEntity;
		getHibernateTemplate().update(addressEntity);
		return addressEntity;
	}
	
	public void delete(AddressEntity addressEntity) {
//		entityManager.getTransaction().begin();
//		entityManager.remove(entityManager.merge(addressEntity));
//		entityManager.getTransaction().commit();
		getHibernateTemplate().delete(addressEntity);
	}
}
