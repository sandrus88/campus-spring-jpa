package org.sg.service;

import org.sg.entities.CourseEntity;
import org.sg.entities.StudentEntity;

public interface ExamService {
	StudentEntity insertStudent(StudentEntity studentEntity);
	StudentEntity getStudent(Integer id);
	StudentEntity updateStudent(StudentEntity studentEntity);
	boolean deleteStudent(Integer id);
	CourseEntity insertCourse(CourseEntity courseEntity);
	CourseEntity getCourse(Integer id);
	CourseEntity updateCourse(CourseEntity courseEntity);
	boolean deleteCourse(Integer id);
}
