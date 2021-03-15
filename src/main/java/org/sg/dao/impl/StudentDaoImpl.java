package org.sg.dao.impl;

import org.sg.dao.GenericDao;
import org.sg.dao.StudentDao;
import org.sg.entities.StudentEntity;

public class StudentDaoImpl extends GenericDao implements StudentDao {

	public StudentEntity createStudent(StudentEntity studentEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(studentEntity);
		entityManager.getTransaction().commit();
		return studentEntity;
	}

	public StudentEntity get(int id) {
		StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
		return studentEntity;
	}

	public StudentEntity update(StudentEntity studentEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(studentEntity);
		entityManager.getTransaction().commit();
		return studentEntity;
	}

	public boolean delete(int id) {
		entityManager.getTransaction().begin();
		if (id == 0) {
			return false;
		} else {
			StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
			entityManager.persist(studentEntity);
			entityManager.getTransaction().commit();
			return true;
		}
	}
}
