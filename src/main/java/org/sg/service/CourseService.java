package org.sg.service;

import org.sg.entities.CourseEntity;

public interface CourseService {
	CourseEntity insert(CourseEntity courseEntity);
	CourseEntity get(Integer id);
	CourseEntity update(CourseEntity courseEntity);
	boolean delete(Integer id);
}
