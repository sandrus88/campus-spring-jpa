package org.sg.service.impl;

import java.util.List;

import org.sg.dao.CourseDao;
import org.sg.dao.TopicDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.dao.impl.TopicDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.TopicEntity;
import org.sg.service.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;
	private TopicDao topicDao;

	public CourseServiceImpl() {
		courseDao = new CourseDaoImpl();
		topicDao = new TopicDaoImpl();
	}

	@Override
	public CourseEntity insert(CourseEntity courseEntity) {
		return courseDao.insert(courseEntity);
	}

	@Override
	public CourseEntity get(Integer id) {
		return courseDao.get(id);
	}

	@Override
	public CourseEntity update(CourseEntity courseEntity) {
		return courseDao.update(courseEntity);
	}

	@Override
	public boolean delete(Integer id) {
		return courseDao.delete(id);
	}

	@Override
	public List<CourseEntity> getAllCourses() {
		return courseDao.getAll();
	}

	@Override
	public List<TopicEntity> getAllTopics() {
		return topicDao.getAll();
	}
}
