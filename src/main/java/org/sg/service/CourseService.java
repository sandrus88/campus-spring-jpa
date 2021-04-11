package org.sg.service;

import java.util.List;

import org.sg.entities.CourseEntity;
import org.sg.entities.TopicEntity;

public interface CourseService {
	CourseEntity insert(CourseEntity courseEntity);
	CourseEntity get(Integer id);
	CourseEntity update(CourseEntity courseEntity);
	List<CourseEntity> getAllCourses();
	List<TopicEntity> getAllTopics();
	boolean delete(Integer id);
}
