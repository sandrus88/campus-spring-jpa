package org.sg.dao.impl;

import javax.persistence.EntityManager;

import org.sg.dao.interfaces.StudentDao;
import org.sg.entities.StudentEntity;
import org.sg.utils.EntityManagerUtil;

public class StudentDaoImpl implements StudentDao {

	public StudentEntity createStudent(StudentEntity studentEntity) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(studentEntity);
		em.getTransaction().commit();
		return studentEntity;
	}

	public StudentEntity get(Integer id) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		StudentEntity studentEntity = em.find(StudentEntity.class, id);
		return studentEntity;
	}

	public StudentEntity update(StudentEntity studentEntity) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(studentEntity);
		em.getTransaction().commit();
		return studentEntity;
	}

	public boolean delete(Integer id) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		em.getTransaction().begin();
		if (id.equals(null)) {
			return false;
		} else {
			StudentEntity studentEntity = em.find(StudentEntity.class, id);
			em.remove(studentEntity);
			em.getTransaction().commit();
			return true;
		}
	}
}
