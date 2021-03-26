package org.sg.service.impl;

import org.sg.dao.GenericDao;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.StudentService;

public class StudentServiceImpl extends GenericDao implements StudentService{

	public StudentEntity insert(StudentEntity studentEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(studentEntity);
		entityManager.getTransaction().commit();
		return studentEntity;
	}

	public StudentEntity getStudent(Integer id) {
		StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
		return studentEntity;
	}

	public StudentEntity update(StudentEntity studentEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(studentEntity);
		entityManager.getTransaction().commit();
		return studentEntity;
	}

	public boolean deleteStudent(Integer id) {
		StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
		if (studentEntity != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(studentEntity);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

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

}
