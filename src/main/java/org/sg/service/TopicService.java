package org.sg.service;

import org.sg.entities.TopicEntity;

public interface TopicService {
	TopicEntity insert(TopicEntity topicEntity);
	TopicEntity get(Integer id);
	TopicEntity update(TopicEntity topicEntity);
	boolean delete(Integer id);
}
