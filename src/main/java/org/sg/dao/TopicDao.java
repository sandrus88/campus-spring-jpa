package org.sg.dao;

import org.sg.entities.TopicEntity;

public interface TopicDao {
	TopicEntity insert(TopicEntity topicEntity);
	TopicEntity get(Integer id);
	TopicEntity update(TopicEntity topicEntity);
	boolean delete(Integer id);
}
