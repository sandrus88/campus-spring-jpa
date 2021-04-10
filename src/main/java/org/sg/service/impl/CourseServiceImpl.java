package org.sg.service.impl;

import org.sg.dao.CourseDao;
import org.sg.entities.CourseEntity;
import org.sg.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	final private CourseDao courseDao;
	
	@Autowired
	public CourseServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
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
}
