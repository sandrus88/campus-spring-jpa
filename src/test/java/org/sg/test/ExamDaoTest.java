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
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.ExamService;
import org.sg.service.impl.ExamServiceImpl;

public class ExamDaoTest {

	private static Logger logger = LogManager.getLogger(ExamDaoTest.class);
	private ExamService examDao = new ExamServiceImpl();
	

	@Test
	public void test_get_fromStudent() {
		//Given
		final Integer studentId = 1;
		
		//When
		StudentEntity studentEntity = examDao.getStudent(studentId);
		
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
		CourseEntity courseEntity = examDao.get(courseId);;
		
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
		StudentEntity studentEntity = examDao.getStudent(studentId);
		CourseEntity courseEntity = createCourse();
		examDao.insert(courseEntity);
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		studentEntity.addExam(examEntity);
		examDao.update(studentEntity);
		StudentEntity studentEntityDb = examDao.getStudent(studentId);
		
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
		StudentEntity studentEntity = examDao.getStudent(studentId);
		CourseEntity courseEntity = examDao.get(courseId);;
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		studentEntity.addExam(examEntity);
		examDao.update(studentEntity);
		StudentEntity studentEntityDb = examDao.getStudent(studentId);
		
		//Then
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
	}
	
	@Test
	public void test_update() throws ParseException {
		//Given
		final Integer studentId = 2;
		final Integer examId = 2;
		
		//When
		StudentEntity studentEntity = examDao.getStudent(studentId);
		ExamEntity examEntity = studentEntity.getExamById(examId);
		examEntity = updateExam(examEntity);
		examDao.update(studentEntity);
		StudentEntity studentEntityDb = examDao.getStudent(studentId);
		
		//Then
		assertNotEquals(studentEntityDb.getExamById(examId), studentEntity.getExamById(examId));
	}
	
	@Test
	public void test_delete() {
		//Given
		final Integer studentId = 3;
		final Integer examId = 4;
		
		//When
		StudentEntity studentEntity = examDao.getStudent(studentId);
		studentEntity.removeExamById(examId);
		examDao.update(studentEntity);
		StudentEntity studentEntityDb = examDao.getStudent(studentId);
		
		//Then
		assertNull(studentEntityDb.getExamById(examId));
	}
	
	@Test
	public void test_delete_all() {
		//Given
		final Integer studentId = 4;
		
		//When
		StudentEntity studentEntity = examDao.getStudent(studentId);
		studentEntity.getExams().clear();
		examDao.update(studentEntity);
		StudentEntity studentEntityDb = examDao.getStudent(studentId);
		
		//Then
		assertNotNull(studentEntityDb);
		assertTrue(studentEntityDb.getExams().isEmpty());
	}
	
	@Test
	public void test_delete_student_withExams() {
		//Given
		final Integer studentId = 5;
		
		//When
		boolean deleting = examDao.deleteStudent(studentId);
		StudentEntity studentEntityDb = examDao.getStudent(studentId);
		
		//Then
		assertTrue(deleting);
		assertNull(studentEntityDb);
	}
}