package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.entities.CourseEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.CourseService;
import org.sg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionsTest extends AbstractSpringTest {
	private static Logger logger = LogManager.getLogger(SubscriptionsTest.class);

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseDao;

	@Test
	public void test_getCourses_fromStudent() {
		// Given
		final Integer studentId = 2;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentId);

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
	public void test_addCourse_forStudent() {
		// Given
		final Integer studentId = 7;
		final Integer courseId = 205;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		studentEntity.addCourse(courseEntity);
		studentService.update(studentEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentId);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(1, studentEntityDb.getCourses().size());
		assertNotNull(studentEntityDb.getCourses().get(0));
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		// check course integrity
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(1, courseEntityDb.getStudents().size());
		assertNotNull(courseEntityDb.getStudents().get(0));
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void test_add2Courses_forStudent() {
		// Given
		final Integer studentId = 6;
		final Integer courseOneId = 203;
		final Integer courseTwoId = 204;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseOneId);
		CourseEntity course2Entity = courseDao.get(courseTwoId);
		studentEntity.addCourse(courseEntity);
		studentEntity.addCourse(course2Entity);
		studentService.update(studentEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentId);
		CourseEntity courseEntityDb = courseDao.get(courseTwoId);

		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(2, studentEntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		assertEquals(course2Entity, studentEntityDb.getCourses().get(1));
		// check course integrity
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void test_addStudent_forCourse() {
		// Given
		final Integer studentId = 8;
		final Integer courseId = 206;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		courseDao.update(courseEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentId);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(1, studentEntityDb.getCourses().size());
		assertNotNull(studentEntityDb.getCourses().get(0));
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		// check course integrity
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(1, courseEntityDb.getStudents().size());
		assertNotNull(courseEntityDb.getStudents().get(0));
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void test_add2Students_forCourse() {
		// Given
		final Integer studentOneId = 9;
		final Integer studentTwoId = 10;
		final Integer courseId = 208;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentOneId);
		StudentEntity student2Entity = studentService.getStudent(studentTwoId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		courseEntity.addStudent(student2Entity);
		courseDao.update(courseEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentOneId);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		// check course integrity
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(2, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
		assertEquals(student2Entity, courseEntityDb.getStudents().get(1));
	}

	@Test
	public void test_deleteStudent_fromCourse() {
		// Given
		final Integer studentId = 21;
		final Integer courseId = 209;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
		courseDao.update(courseEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentId);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		// check course integrity
		assertEquals(0, courseEntityDb.getStudents().size());
	}

	@Test
	public void test_deleteStudent_fromCourse_when2StudentsPresent() {
		// Given
		final Integer studentOneId = 22;
		final Integer studentTwoId = 23;
		final Integer courseId = 210;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentOneId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
		courseDao.update(courseEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentOneId);
		StudentEntity student2EntityDb = studentService.getStudent(studentTwoId);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(1, student2EntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		assertEquals(courseEntity, student2EntityDb.getCourses().get(0));
		// check course integrity
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void test_deleteCourse_fromStudent() {
		// Given
		final Integer studentId = 24;
		final Integer courseId = 211;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		studentEntity.removeCourse(courseEntity);
		studentService.update(studentEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentId);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(0, studentEntityDb.getCourses().size());
		// check course integrity
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void test_deleteCourse_fromStudent_when2CoursesPresent() {
		// Given
		final Integer studentId = 25;
		final Integer courseOneId = 212;
		final Integer courseTwoId = 213;

		// When
		StudentEntity studentEntity = studentService.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseOneId);
		CourseEntity course2Entity = courseDao.get(courseTwoId);
		studentEntity.removeCourse(courseEntity);
		studentService.update(studentEntity);
		StudentEntity studentEntityDb = studentService.getStudent(studentId);
		CourseEntity courseEntityDb = courseDao.get(courseOneId);
		CourseEntity course2EntityDb = courseDao.get(courseTwoId);

		// Then
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(course2Entity, studentEntityDb.getCourses().get(0));
		// check course integrity
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
		assertEquals(1, course2EntityDb.getStudents().size());
		assertEquals(studentEntity, course2EntityDb.getStudents().get(0));
	}
}
