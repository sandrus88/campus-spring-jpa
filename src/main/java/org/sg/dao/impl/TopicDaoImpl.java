package org.sg.dao.impl;

import java.util.List;

import org.sg.dao.GenericDao;
import org.sg.dao.TopicDao;
import org.sg.entities.AddressEntity;
import org.sg.entities.TopicEntity;

public class TopicDaoImpl extends GenericDao implements TopicDao{

	@Override
	public TopicEntity insert(TopicEntity topicEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(topicEntity);
		entityManager.getTransaction().commit();
		return topicEntity;
	}

	@Override
	public TopicEntity get(Integer id) {
		TopicEntity topicEntity = entityManager.find(TopicEntity.class, id);
		return topicEntity;
	}

	@Override
	public TopicEntity update(TopicEntity topicEntity) {
		entityManager.getTransaction().begin();
		entityManager.persist(topicEntity);
		entityManager.getTransaction().commit();
		return topicEntity;
	}

	@Override
	public boolean delete(Integer id) {
		TopicEntity topicEntity = entityManager.find(TopicEntity.class, id);
		if (topicEntity != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(topicEntity);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public List<TopicEntity> getAll() {
		entityManager.getTransaction().begin();
		List<TopicEntity> topics = entityManager.createQuery("from TopicEntity", TopicEntity.class).getResultList();
		entityManager.getTransaction().commit();
		return topics;
	}

}
