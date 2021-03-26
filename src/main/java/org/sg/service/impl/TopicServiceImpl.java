package org.sg.service.impl;

import org.sg.dao.GenericDao;
import org.sg.entities.TopicEntity;
import org.sg.service.TopicService;

public class TopicServiceImpl extends GenericDao implements TopicService{

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
}
