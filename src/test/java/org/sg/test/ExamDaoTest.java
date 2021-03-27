package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createCourse;
import static org.sg.test.util.EntityUtils.createExam;
import static org.sg.test.util.EntityUtils.updateExam;

import java.text.ParseException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.dao.CourseDao;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;

public class ExamDaoTest {
	private static Logger logger = LogManager.getLogger(ExamDaoTest.class);
	private CourseDao courseDao = new CourseDaoImpl();
	private StudentDao studentDao = new StudentDaoImpl();
	

	@Test
	public void test_get_fromStudent() {
		//Given
		final Integer studentId = 1;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		
		//Then
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getExams());
		assertEquals(studentEntity.getExams().get(0).getId(), Integer.valueOf(9));
		assertEquals(studentEntity.getExams().get(1).getId(), Integer.valueOf(10));
		assertEquals(studentEntity.getExams().get(2).getId(), Integer.valueOf(13));
		assertEquals(studentEntity.getExams().get(3).getId(), Integer.valueOf(14));
	}

	@Test
	public void test_get_fromCourse() {
		//Given
		final Integer courseId = 201;
		
		//When
		CourseEntity courseEntity = courseDao.get(courseId);;
		
		//Then
		assertNotNull(courseEntity);
		assertNotNull(courseEntity.getExams());
		assertEquals(courseEntity.getExams().get(0).getId(), Integer.valueOf(1));
		assertEquals(courseEntity.getExams().get(1).getId(), Integer.valueOf(2));
		assertEquals(courseEntity.getExams().get(2).getId(), Integer.valueOf(4));
		assertEquals(courseEntity.getExams().get(3).getId(), Integer.valueOf(12));
		assertEquals(courseEntity.getExams().get(4).getId(), Integer.valueOf(13));
	}
	
	@Test
	public void test_insert_toStudent_forNewCourse() throws ParseException {
		//Given
		final Integer studentId = 6;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = createCourse();
		courseDao.insert(courseEntity);
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		studentEntity.addExam(examEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
	}
	
	@Test
	public void test_insert_toStudent_forExistingCourse() throws ParseException {
		//Given
		final Integer studentId = 7;
		final Integer courseId = 206;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		CourseEntity courseEntity = courseDao.get(courseId);;
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		studentEntity.addExam(examEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
	}
	
	@Test
	public void test_update() throws ParseException {
		//Given
		final Integer studentId = 2;
		final Integer examId = 2;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		ExamEntity examEntity = studentEntity.getExamById(examId);
		updateExam(examEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertNotEquals(studentEntityDb.getExamById(examId), studentEntity.getExamById(examId));
	}
	
	@Test
	public void test_delete() {
		//Given
		final Integer studentId = 3;
		final Integer examId = 4;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		studentEntity.removeExamById(examId);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertNull(studentEntityDb.getExamById(examId));
	}
	
	@Test
	public void test_delete_all() {
		//Given
		final Integer studentId = 4;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		studentEntity.getExams().clear();
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertNotNull(studentEntityDb);
		assertEquals(0, studentEntityDb.getExams().size());
	}
	
	@Test
	public void test_delete_student_withExams() {
		//Given
		final Integer studentId = 5;
		
		//When
		boolean deleting = studentDao.delete(studentId);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertTrue(deleting);
		assertNull(studentEntityDb);
	}
}