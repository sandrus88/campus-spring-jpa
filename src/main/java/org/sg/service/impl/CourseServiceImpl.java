package org.sg.service.impl;

import org.sg.dao.CourseDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.service.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;

	public CourseServiceImpl() {
		courseDao = new CourseDaoImpl();
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
