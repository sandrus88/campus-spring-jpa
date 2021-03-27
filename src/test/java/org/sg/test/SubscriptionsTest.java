package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	private static Logger logger = LogManager.getLogger(SubscriptionsTest.class);
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
		courseEntity.addStudent(studentEntity);
		courseDao.update(courseEntity);
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
		courseDao.update(courseEntity);
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
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		CourseEntity courseEntityDb = courseDao.get(courseTwoId);

		// Then
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void add_studentForCourse_checkStudentIntegrity() {
		// Given
		final Integer studentId = 8;
		final Integer courseId = 206;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		studentEntity.addCourse(courseEntity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);

		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(1, studentEntityDb.getCourses().size());
		assertNotNull(studentEntityDb.getCourses().get(0));
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
	}

	@Test
	public void add_studentForCourse_checkCourseIntegrity() {
		// Given
		final Integer studentId = 8;
		final Integer courseId = 206;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		studentEntity.addCourse(courseEntity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(1, courseEntityDb.getStudents().size());
		assertNotNull(courseEntityDb.getStudents().get(0));
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void add_2StudentForCourse_checkStudentIntegrity() {
		// Given
		final Integer studentOneId = 9;
		final Integer studentTwoId = 10;
		final Integer courseId = 208;

		// When
		StudentEntity studentEntity = studentDao.get(studentOneId);
		StudentEntity student2Entity = studentDao.get(studentTwoId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		courseEntity.addStudent(student2Entity);
		studentEntity.addCourse(courseEntity);
		student2Entity.addCourse(courseEntity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentOneId);

		// Then
		assertNotNull(studentEntityDb.getCourses());
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
	}

	@Test
	public void add_2StudentForCourse_checkCourseIntegrity() {
		// Given
		final Integer studentOneId = 9;
		final Integer studentTwoId = 10;
		final Integer courseId = 208;

		// When
		StudentEntity studentEntity = studentDao.get(studentOneId);
		StudentEntity student2Entity = studentDao.get(studentTwoId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		courseEntity.addStudent(student2Entity);
		studentEntity.addCourse(courseEntity);
		student2Entity.addCourse(courseEntity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntityDb.getStudents());
		assertEquals(2, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
		assertEquals(student2Entity, courseEntityDb.getStudents().get(1));
	}

	@Test
	public void delete_studentFromCourse_checkStudentIntegrity() {
		// Given
		final Integer studentId = 21;
		final Integer courseId = 209;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);

		// Then
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
	}

	@Test
	public void delete_studentFromCourse_checkCourseIntegrity() {
		// Given
		final Integer studentId = 21;
		final Integer courseId = 209;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(0, courseEntityDb.getStudents().size());
	}

	@Test
	public void delete_studentFromCourse_when2StudentsPresent_checkStudentIntegrity() {
		// Given
		final Integer studentOneId = 22;
		final Integer studentTwoId = 23;
		final Integer courseId = 210;

		// When
		StudentEntity studentEntity = studentDao.get(studentOneId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentOneId);
		StudentEntity student2EntityDb = studentDao.get(studentTwoId);

		// Then
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(1, student2EntityDb.getCourses().size());
		assertEquals(courseEntity, studentEntityDb.getCourses().get(0));
		assertEquals(courseEntity, student2EntityDb.getCourses().get(0));
	}

	@Test
	public void delete_studentFromCourse_when2StudentsPresent_checkCourseIntegrity() {
		// Given
		final Integer studentOneId = 22;
		final Integer studentTwoId = 23;
		final Integer courseId = 210;

		// When
		StudentEntity studentEntity = studentDao.get(studentOneId);
		StudentEntity student2Entity = studentDao.get(studentTwoId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(student2Entity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void delete_courseFromStudent_checkStudentIntegrity() {
		// Given
		final Integer studentId = 24;
		final Integer courseId = 211;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		studentEntity.removeCourse(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);

		// Then
		assertEquals(0, studentEntityDb.getCourses().size());
	}

	@Test
	public void delete_courseFromStudent_checkCourseIntegrity() {
		// Given
		final Integer studentId = 24;
		final Integer courseId = 211;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		studentEntity.removeCourse(courseEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

	@Test
	public void delete_courseFromStudent_when2CoursesPresent_checkStudentIntegrity() {
		// Given
		final Integer studentId = 25;
		final Integer courseOneId = 212;
		final Integer courseTwoId = 213;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseOneId);
		CourseEntity course2Entity = courseDao.get(courseTwoId);
		studentEntity.removeCourse(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);

		// Then
		assertEquals(1, studentEntityDb.getCourses().size());
		assertEquals(course2Entity, studentEntityDb.getCourses().get(0));
	}

	@Test
	public void delete_courseFromStudent_when2CoursesPresent_checkCourseIntegrity() {
		// Given
		final Integer studentId = 25;
		final Integer courseOneId = 212;
		final Integer courseTwoId = 213;

		// When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseTwoId);
		studentEntity.removeCourse(courseEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseOneId);
		CourseEntity course2EntityDb = courseDao.get(courseTwoId);

		// Then
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(1, course2EntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
		assertEquals(studentEntity, course2EntityDb.getStudents().get(0));
	}

}
