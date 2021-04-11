package org.sg.service;

import java.util.List;

import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;

public interface ExamService {

	StudentEntity insertStudent(StudentEntity studentEntity);
	StudentEntity getStudent(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	List<StudentEntity> getAllStudents();
	boolean deleteStudent(Integer id);

    CourseEntity insertCourse(CourseEntity courseEntity);
    CourseEntity getCourse(Integer id);
    CourseEntity updateCourse(CourseEntity courseEntity);
    boolean deleteCourse(Integer id);
	List<CourseEntity> getAllCourses();

	List<ExamEntity> getAllExams();

}
