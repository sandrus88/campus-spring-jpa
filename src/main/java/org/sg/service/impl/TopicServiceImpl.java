package org.sg.service.impl;

import org.sg.dao.TopicDao;
import org.sg.dao.impl.TopicDaoImpl;
import org.sg.entities.TopicEntity;
import org.sg.service.TopicService;

public class TopicServiceImpl implements TopicService{
	
	private TopicDao topicDao;
	
	public TopicServiceImpl() {
		topicDao = new TopicDaoImpl();
	}

	@Override
	public TopicEntity insert(TopicEntity topicEntity) {
		return topicDao.insert(topicEntity);
	}

	@Override
	public TopicEntity get(Integer id) {
		return topicDao.get(id);
	}

	@Override
	public TopicEntity update(TopicEntity topicEntity) {
		return topicDao.update(topicEntity);
	}

	@Override
	public boolean delete(Integer id) {
		return topicDao.delete(id);
	}
}
