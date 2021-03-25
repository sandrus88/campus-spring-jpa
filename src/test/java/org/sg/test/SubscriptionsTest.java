package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.sg.test.util.EntityUtils.createCourse;
import static org.sg.test.util.EntityUtils.createStudent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.dao.CourseDao;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.StudentEntity;

public class SubscriptionsTest {
	private static Logger logger = LogManager.getLogger(ExamDaoTest.class);
	private StudentDao studentDao = new StudentDaoImpl();
	private CourseDao courseDao = new CourseDaoImpl();

	@Test
	public void test_getCourses_fromStudent() {
		// Given
		final Integer studentId = 2;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);

		// Then
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getCourses());
		assertEquals(2, studentEntity.getCourses().size());
		assertEquals(studentEntity.getCourses().get(0).getId(), Integer.valueOf(201));
		assertEquals(studentEntity.getCourses().get(1).getId(), Integer.valueOf(207));
	}

	@Test
	public void test_getStudents_fromCourse() {
		// Given
		final Integer courseId = 202;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntity);
		assertNotNull(courseEntity.getStudents());
		assertEquals(2, courseEntity.getStudents().size());
		assertEquals(courseEntity.getStudents().get(0).getId(), Integer.valueOf(4));
		assertEquals(courseEntity.getStudents().get(1).getId(), Integer.valueOf(5));
	}
	
	@Test
	public void add_courseForStudent_checkStudentIntegrity() {
		// Given
		final Integer studentId = 7;
		final Integer courseId = 205;
		
		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		studentEntity.addCourse(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(1, studentEntityDb.getCourses().size());
		assertNotNull(studentEntityDb.getCourses().get(0));
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
	}
	
	@Test
	public void add_courseForStudent_checkCourseIntegrity() {
		// Given
		final Integer studentId = 7;
		final Integer courseId = 205;
		
		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		studentEntity.addCourse(courseEntity);
		studentDao.update(studentEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);
		
		// Then
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(1, courseEntityDb.getStudents().size());
		assertNotNull(courseEntityDb.getStudents().get(0));
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}
	
	@Test
	public void add_2CoursesForStudent_checkStudentIntegrity() {
		// Given
		final Integer studentId = 6;
		final Integer courseOneId = 203;
		final Integer courseTwoId = 204;
		
		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseOneId);
		CourseEntity course2Entity = courseDao.get(courseTwoId);
		studentEntity.addCourse(courseEntity);
		studentEntity.addCourse(course2Entity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(2, studentEntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		assertEquals(course2Entity, studentEntityDb.getCourses().get(1));
	}
	
	@Test
	public void add_2CoursesForStudent_checkCoursesIntegrity() {
		// Given
		final Integer studentId = 6;
		final Integer courseOneId = 203;
		final Integer courseTwoId = 204;
		
		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseOneId);
		CourseEntity course2Entity = courseDao.get(courseTwoId);
		studentEntity.addCourse(courseEntity);
		studentEntity.addCourse(course2Entity);
		studentDao.update(studentEntity);
		
		CourseEntity courseEntityDb = courseDao.get(courseTwoId);
		
		// Then
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}
	
	@Test
	public void add_studentForCourse_checkStudentIntegrity() {
		fail("to be implemented");
	}
	
	@Test
	public void add_studentForCourse_checkCourseIntegrity() {
		fail("to be implemented");
	}
	
	@Test
	public void add_2StudentForCourse_checkStudentIntegrity() {
		fail("to be implemented");
	}
	
	@Test
	public void add_2StudentForCourse_checkCourseIntegrity() {
		fail("to be implemented");
	}
	
	@Test
	public void delete_studentFromCourse_checkStudentIntegrity() {
		fail("to be implemented");
		//lista corsi sara 0
	}
	
	@Test
	public void delete_studentFromCourse_checkCourseIntegrity() {
		fail("to be implemented");
		//lista studenti  sara 0
	}
	@Test
	public void delete_studentForCourse_when2StudentsPresent_checkStudentIntegrity() {
		fail("to be implemented");
		//lista corsi studente 1 e' 0
		//lista corsi studente 2 e' 1
	}
	
	@Test
	public void delete_studentForCourse_when2StudentsPresent_checkCourseIntegrity() {
		fail("to be implemented");
		//lista studenti sara 1
	}
	
}
