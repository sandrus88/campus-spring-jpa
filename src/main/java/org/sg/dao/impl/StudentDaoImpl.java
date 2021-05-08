package org.sg.dao.impl;

import java.util.List;

import org.sg.dao.GenericDao;
import org.sg.dao.StudentDao;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends GenericDao implements StudentDao {

	public StudentEntity insert(StudentEntity studentEntity) {
		entityManager.persist(studentEntity);
		return studentEntity;
	}

	public StudentEntity get(Integer id) {
		StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
		return studentEntity;
	}

	public StudentEntity update(StudentEntity studentEntity) {
		entityManager.merge(studentEntity);
		return studentEntity;
	}

	public boolean delete(Integer id) {
		StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);
		if (studentEntity != null) {
			entityManager.remove(studentEntity);
			return true;
		}
		return false;
	}

	@Override
	public List<StudentEntity> getAll() {
		List<StudentEntity> students = entityManager.createQuery("from StudentEntity", StudentEntity.class).getResultList();
		return students;
	}
}
