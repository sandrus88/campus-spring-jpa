package org.sg.dao;

import java.util.List;

import org.sg.entities.TopicEntity;

public interface TopicDao {
	TopicEntity insert(TopicEntity topicEntity);
	TopicEntity get(Integer id);
	TopicEntity update(TopicEntity topicEntity);
	List<TopicEntity> getAll();
	boolean delete(Integer id);
}
