package org.sg.service;

import java.util.List;

import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;

public interface ExamService {
	StudentEntity insert(StudentEntity studentEntity);
	StudentEntity getStudent(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	List<StudentEntity> getAllStudents();
	boolean deleteStudent(Integer id);
	CourseEntity insert(CourseEntity courseEntity);
	CourseEntity get(Integer id);
	CourseEntity update(CourseEntity courseEntity);
	List<CourseEntity> getAllCourses();
	boolean delete(Integer id);
	List<ExamEntity> getAllExams();
}
