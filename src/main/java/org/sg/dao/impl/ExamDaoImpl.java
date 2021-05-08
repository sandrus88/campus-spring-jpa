package org.sg.dao.impl;

import java.util.List;

import org.sg.dao.ExamDao;
import org.sg.dao.GenericDao;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ExamDaoImpl extends GenericDao implements ExamDao{
	
	public ExamEntity insert(ExamEntity examEntity) {
		entityManager.persist(examEntity);
		return examEntity;
	}

	public ExamEntity get(Integer id) {
		ExamEntity examEntity = entityManager.find(ExamEntity.class, id);
		return examEntity;
	}

	public ExamEntity update(ExamEntity examEntity) {
		entityManager.merge(examEntity);
		return examEntity;
	}

	public boolean delete(Integer id) {
		ExamEntity examEntity = entityManager.find(ExamEntity.class, id);
		if (examEntity != null) {
			entityManager.remove(examEntity);
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
