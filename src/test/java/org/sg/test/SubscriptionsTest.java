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
import org.sg.service.impl.CourseServiceImpl;
import org.sg.service.impl.StudentServiceImpl;

public class SubscriptionsTest {
	private static Logger logger = LogManager.getLogger(SubscriptionsTest.class);
	private StudentService studentDao = new StudentServiceImpl();
	private CourseService courseDao = new CourseServiceImpl();

	@Test
	public void test_getCourses_fromStudent() {
		// Given
		final Integer studentId = 2;

		// When
		StudentEntity studentEntity = studentDao.getStudent(studentId);

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
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		studentEntity.addCourse(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);

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
		StudentEntity studentEntity = studentDao.getStudent(studentId);
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
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseOneId);
		CourseEntity course2Entity = courseDao.get(courseTwoId);
		studentEntity.addCourse(courseEntity);
		studentEntity.addCourse(course2Entity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);

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
		StudentEntity studentEntity = studentDao.getStudent(studentId);
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
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		studentEntity.addCourse(courseEntity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);

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
		StudentEntity studentEntity = studentDao.getStudent(studentId);
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
		StudentEntity studentEntity = studentDao.getStudent(studentOneId);
		StudentEntity student2Entity = studentDao.getStudent(studentTwoId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addStudent(studentEntity);
		courseEntity.addStudent(student2Entity);
		studentEntity.addCourse(courseEntity);
		student2Entity.addCourse(courseEntity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentOneId);

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
		StudentEntity studentEntity = studentDao.getStudent(studentOneId);
		StudentEntity student2Entity = studentDao.getStudent(studentTwoId);
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
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
//		studentEntity.removeCourse(courseEntity);
		courseDao.update(courseEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);
		
		//Then
		assertEquals(0, studentEntityDb.getCourses().size());
	}

	@Test
	public void delete_studentFromCourse_checkCourseIntegrity() {
		// Given
		final Integer studentId = 21;
		final Integer courseId = 209;

		// When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);
		
		//Then
		assertEquals(0, courseEntityDb.getStudents().size());
	}

	@Test
	public void delete_studentFromCourse_when2StudentsPresent_checkStudentIntegrity() {
		// Given
		final Integer studentOneId = 22;
		final Integer studentTwoId = 23;
		final Integer courseId = 210;

		// When
		StudentEntity studentEntity = studentDao.getStudent(studentOneId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(studentEntity);
		courseDao.update(courseEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentOneId);
		StudentEntity student2EntityDb = studentDao.getStudent(studentTwoId);
		
		//Then
		assertEquals(0, studentEntityDb.getCourses().size());
		assertEquals(1, student2EntityDb.getCourses().size());
	}

	@Test
	public void delete_studentFromCourse_when2StudentsPresent_checkCourseIntegrity() {
		// Given
		final Integer studentOneId = 22;
		final Integer studentTwoId = 23;
		final Integer courseId = 210;

		// When
		StudentEntity studentEntity = studentDao.getStudent(studentOneId);
		StudentEntity student2Entity = studentDao.getStudent(studentTwoId);
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeStudent(student2Entity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);
		
		//Then
		assertEquals(1, courseEntityDb.getStudents().size());
		assertEquals(studentEntity, courseEntityDb.getStudents().get(0));
	}

}
