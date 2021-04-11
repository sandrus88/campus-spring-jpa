package org.sg.dao;

import java.util.List;

import org.sg.entities.CourseEntity;

public interface CourseDao {
		CourseEntity insert(CourseEntity courseEntity);
		CourseEntity get(Integer id);
		CourseEntity update(CourseEntity courseEntity);
		List<CourseEntity> getAll();
		boolean delete(Integer id);
	}
