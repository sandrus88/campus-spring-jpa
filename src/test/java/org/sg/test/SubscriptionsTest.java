package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
	public static Logger logger = LogManager.getLogger(ExamDaoTest.class);
	StudentDao studentDao = new StudentDaoImpl();
	CourseDao courseDao = new CourseDaoImpl();

	@Test
	public void test_get_fromStudent() {
		// Given
		final Integer studentId = 2;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);

		// Then
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getCourses());
		assertEquals(studentEntity.getCourses().get(0).getId(), Integer.valueOf(201));
		assertEquals(studentEntity.getCourses().get(1).getId(), Integer.valueOf(207));
	}

	@Test
	public void test_get_fromCourse() {
		// Given
		final Integer courseId = 202;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntity);
		assertNotNull(courseEntity.getStudents());
		assertEquals(courseEntity.getStudents().get(0).getId(), Integer.valueOf(4));
		assertEquals(courseEntity.getStudents().get(1).getId(), Integer.valueOf(5));
	}

	@Test
	public void test_insert() {
		// Given
		StudentEntity studentEntity = createStudent();
		CourseEntity courseEntity = createCourse();

		// When
		List<StudentEntity> studentsList = new ArrayList<StudentEntity>();
		List<CourseEntity> coursesList = new ArrayList<CourseEntity>();
		courseEntity.setStudents(studentsList);
		studentEntity.setCourses(coursesList);
		studentEntity.addCourse(courseEntity);
		courseEntity = courseDao.insert(courseEntity);
		studentEntity = studentDao.insert(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());

		// Then
		assertNotNull(studentEntity.getCourses());
		assertNotNull(courseEntity.getStudents());
		assertEquals(studentEntityDb.getCourses(), studentEntity.getCourses());
		assertEquals(courseEntityDb.getStudents(), courseEntity.getStudents());
	}

	@Test
	public void add_for_existingStudentAndCourse() {
		// Given
		final Integer studentId = 6;
		final Integer courseId = 203;
		final Integer secondCourseId = 204;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		CourseEntity secondCourseEntity = courseDao.get(secondCourseId);
		studentEntity.addCourse(courseEntity);
		studentEntity.addCourse(secondCourseEntity);
		studentEntity = studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());
		CourseEntity secondCourseEntityDb = courseDao.get(secondCourseEntity.getId());

		// Then
		assertNotNull(studentEntity.getCourses());
		assertNotNull(courseEntity.getStudents());
		assertNotNull(secondCourseEntity.getStudents());
		assertEquals(studentEntityDb.getCourses(), studentEntity.getCourses());
		assertEquals(courseEntityDb.getStudents(), courseEntity.getStudents());
		assertEquals(secondCourseEntityDb.getStudents(), secondCourseEntity.getStudents());
	}
}
