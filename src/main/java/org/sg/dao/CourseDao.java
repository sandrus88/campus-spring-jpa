package org.sg.dao;

import org.sg.entities.CourseEntity;

public interface CourseDao {
		CourseEntity insert(CourseEntity courseEntity);
		CourseEntity get(Integer id);
		CourseEntity update(CourseEntity courseEntity);
		boolean delete(Integer id);
	}
