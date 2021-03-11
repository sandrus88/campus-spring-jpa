package org.sg.services;

import javax.persistence.EntityManager;

import org.sg.entities.StudentEntity;
import org.sg.utils.EntityManagerUtil;

public class StudentService {

	public StudentEntity saveStudent(StudentEntity studentEntity) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(studentEntity);
		em.getTransaction().commit();
		return studentEntity;
	}
}
