package org.sg.service;

import org.sg.entities.CourseEntity;
import org.sg.entities.StudentEntity;

public interface ExamService {
	StudentEntity insert(StudentEntity studentEntity);
	StudentEntity getStudent(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	CourseEntity insert(CourseEntity courseEntity);
	CourseEntity get(Integer id);
	CourseEntity update(CourseEntity courseEntity);
	boolean deleteStudent(Integer id);
	boolean delete(Integer id);
}
