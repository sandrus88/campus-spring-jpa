package org.sg.service.impl;

import org.sg.dao.GenericDao;
import org.sg.entities.CourseEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.ExamService;

public class ExamServiceImpl extends GenericDao implements ExamService{

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
	public CourseEntity insert(CourseEntity courseEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(courseEntity);
		entityManager.getTransaction().commit();
		return courseEntity;
	}

	@Override
	public CourseEntity get(Integer id) {
		CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
		return courseEntity;
	}

	@Override
	public CourseEntity update(CourseEntity courseEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(courseEntity);
		entityManager.getTransaction().commit();
		return courseEntity;
	}

	@Override
	public boolean delete(Integer id) {
		CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
		if (courseEntity != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(courseEntity);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}


}
