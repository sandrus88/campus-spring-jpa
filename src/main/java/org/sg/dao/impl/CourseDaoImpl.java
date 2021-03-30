package org.sg.dao.impl;

import org.sg.dao.CourseDao;
import org.sg.dao.CustomHibernateDaoSupport;
import org.sg.entities.AddressEntity;
import org.sg.entities.CourseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl extends CustomHibernateDaoSupport implements CourseDao{

	@Override
	public CourseEntity insert(CourseEntity courseEntity) {
//		entityManager.getTransaction().begin();
//		entityManager.persist(courseEntity);
//		entityManager.getTransaction().commit();
//		return courseEntity;
		getHibernateTemplate().save(courseEntity);
		return courseEntity;
	}

	@Override
	public CourseEntity get(Integer id) {
//		CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
//		return courseEntity;
		CourseEntity courseEntity = getHibernateTemplate().get(CourseEntity.class, id);
		return courseEntity;
	}

	@Override
	public CourseEntity update(CourseEntity courseEntity) {
//		entityManager.getTransaction().begin();
//		entityManager.persist(courseEntity);
//		entityManager.getTransaction().commit();
//		return courseEntity;
		getHibernateTemplate().update(courseEntity);
		return courseEntity;
	}

	@Override
	public boolean delete(Integer id) {
//		CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
//		if (courseEntity != null) {
//			entityManager.getTransaction().begin();
//			entityManager.remove(courseEntity);
//			entityManager.getTransaction().commit();
//			return true;
//		}
//		return false;
		CourseEntity courseEntity = getHibernateTemplate().get(CourseEntity.class, id);
		if(courseEntity != null) {
			getHibernateTemplate().delete(courseEntity);
			return true;
		}
		return false;
	}

}
