package org.sg.dao.impl;

import java.util.List;

import org.sg.dao.CourseDao;
import org.sg.dao.GenericDao;
import org.sg.entities.CourseEntity;

public class CourseDaoImpl extends GenericDao implements CourseDao{

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
	
	@Override
	public List<CourseEntity> getAll() {
		entityManager.getTransaction().begin();
		List<CourseEntity> courses = entityManager.createQuery("from CourseEntity", CourseEntity.class).getResultList();
		entityManager.getTransaction().commit();
		return courses;
	}

}
