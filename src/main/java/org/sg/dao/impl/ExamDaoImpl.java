package org.sg.dao.impl;

import java.util.List;

import org.sg.dao.ExamDao;
import org.sg.dao.GenericDao;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;

public class ExamDaoImpl extends GenericDao implements ExamDao{
	
	public ExamEntity insert(ExamEntity examEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(examEntity);
		entityManager.getTransaction().commit();
		return examEntity;
	}

	public ExamEntity get(Integer id) {
		ExamEntity examEntity = entityManager.find(ExamEntity.class, id);
		return examEntity;
	}

	public ExamEntity update(ExamEntity examEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(examEntity);
		entityManager.getTransaction().commit();
		return examEntity;
	}

	public boolean delete(Integer id) {
		ExamEntity examEntity = entityManager.find(ExamEntity.class, id);
		if (examEntity != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(examEntity);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public List<ExamEntity> getAll() {
		entityManager.getTransaction().begin();
		List<ExamEntity> exams = entityManager.createQuery("from ExamEntity", ExamEntity.class).getResultList();
		entityManager.getTransaction().commit();
		return exams;
	}
}
