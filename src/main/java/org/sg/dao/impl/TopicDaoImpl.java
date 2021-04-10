package org.sg.dao.impl;

import org.sg.dao.GenericDao;
import org.sg.dao.TopicDao;
import org.sg.entities.TopicEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TopicDaoImpl extends GenericDao implements TopicDao{

	@Override
	public TopicEntity insert(TopicEntity topicEntity) {
		entityManager.persist(topicEntity);
		return topicEntity;
	}

	@Override
	public TopicEntity get(Integer id) {
		TopicEntity topicEntity = entityManager.find(TopicEntity.class, id);
		return topicEntity;
	}

	@Override
	public TopicEntity update(TopicEntity topicEntity) {
		entityManager.merge(topicEntity);
		return topicEntity;
	}

	@Override
	public boolean delete(Integer id) {
		TopicEntity topicEntity = entityManager.find(TopicEntity.class, id);
		if (topicEntity != null) {
			entityManager.remove(topicEntity);
			return true;
		}
		return false;
	}

}
