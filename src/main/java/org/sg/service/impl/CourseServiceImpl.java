package org.sg.service.impl;

import org.sg.dao.GenericDao;
import org.sg.entities.CourseEntity;
import org.sg.service.CourseService;

public class CourseServiceImpl extends GenericDao implements CourseService{

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
