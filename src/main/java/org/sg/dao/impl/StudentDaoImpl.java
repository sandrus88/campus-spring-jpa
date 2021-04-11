package org.sg.dao.impl;

import java.util.List;

import org.sg.dao.GenericDao;
import org.sg.dao.StudentDao;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;

public class StudentDaoImpl extends GenericDao implements StudentDao {

	public StudentEntity insert(StudentEntity studentEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(studentEntity);
		entityManager.getTransaction().commit();
		return studentEntity;
	}

	public StudentEntity get(Integer id) {
		StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
		return studentEntity;
	}

	public StudentEntity update(StudentEntity studentEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(studentEntity);
		entityManager.getTransaction().commit();
		return studentEntity;
	}

	public boolean delete(Integer id) {
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
	public List<StudentEntity> getAll() {
		entityManager.getTransaction().begin();
		List<StudentEntity> students = entityManager.createQuery("from StudentEntity", StudentEntity.class).getResultList();
		entityManager.getTransaction().commit();
		return students;
	}
}
