package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createCourse;
import static org.sg.test.util.EntityUtils.createExam;
import static org.sg.test.util.EntityUtils.createStudent;
import static org.sg.test.util.EntityUtils.updateExam;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.ExamService;
import org.sg.service.impl.ExamServiceImpl;

public class ExamDaoImplTest {
	private static Logger logger = LogManager.getLogger(ExamDaoImplTest.class);
	private ExamService examDao = new ExamServiceImpl();

	@Test
	public void test_getExam_fromStudent() {
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
	public void test_getExam_fromCourse() {
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
	public void test_getAllExams() {
		// Given
		final Integer[] examsId = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

		// When
		List<ExamEntity> exams = examDao.getAllExams();
		System.out.println("List of exams " + exams);

		// Then
		assertNotNull(exams);
		assertEquals(exams.size(), 14);
		assertEquals(exams.get(0).getId(), examsId[0]);
		assertEquals(exams.get(1).getId(), examsId[1]);
		assertEquals(exams.get(2).getId(), examsId[2]);
		assertEquals(exams.get(3).getId(), examsId[3]);
		assertEquals(exams.get(4).getId(), examsId[4]);
		assertEquals(exams.get(5).getId(), examsId[5]);
		assertEquals(exams.get(6).getId(), examsId[6]);
		assertEquals(exams.get(7).getId(), examsId[7]);
		assertEquals(exams.get(8).getId(), examsId[8]);
		assertEquals(exams.get(9).getId(), examsId[9]);
		assertEquals(exams.get(10).getId(), examsId[10]);
		assertEquals(exams.get(11).getId(), examsId[11]);
		assertEquals(exams.get(12).getId(), examsId[12]);
		assertEquals(exams.get(13).getId(), examsId[13]);
	}
	
	@Test
	public void test_insertExam_toStudent_forNewCourse() throws ParseException {
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
	public void test_insertExam_toStudent_forExistingCourse() throws ParseException {
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
	public void test_updateExam() throws ParseException {
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
		assertEquals(studentEntityDb.getExamById(examId), studentEntity.getExamById(examId));
	}
	
	@Test
	public void test_deleteExam() {
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
	public void test_delete_allExams() {
		//Given
		final Integer studentId = 4;
		
		//When
		StudentEntity studentEntity = examDao.getStudent(studentId);
		studentEntity.getExams().clear();
		examDao.update(studentEntity);
		StudentEntity studentEntityDb = examDao.getStudent(studentId);
		
		//Then
		assertNotNull(studentEntityDb);
		assertEquals(0, studentEntityDb.getExams().size());
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
	
	@Test
	public void test_CRUD_exam() throws ParseException {
		// 1. insert a new student
		StudentEntity studentEntity = createStudent();
		examDao.insert(studentEntity);
		assertNotNull(studentEntity.getId());
		
		// 2. insert a new course
		CourseEntity courseEntity = createCourse();
		examDao.insert(courseEntity);
		assertNotNull(courseEntity.getId());
		
		// 3. insert an exam for the student
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		assertNotNull(examEntity);
		examDao.update(studentEntity);
		
		// 4. Get and check if the student and the exam has correctly been fetched
		StudentEntity studentEntityDb = examDao.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertNotNull(studentEntityDb.getExams());
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
		
		// 5. Update the exam, and Get to check if updated correctly
		examEntity = updateExam(examEntity);
		examDao.update(studentEntity);
		studentEntityDb = examDao.getStudent(studentEntity.getId());
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
		
		// 6. Delete the exam, and Get to check if is deleted correctly
		studentEntity.removeExamById(examEntity.getId());
		examDao.update(studentEntity);
		studentEntityDb = examDao.getStudent(studentEntity.getId());
		assertNull(studentEntityDb.getExamById(examEntity.getId()));
	}
}