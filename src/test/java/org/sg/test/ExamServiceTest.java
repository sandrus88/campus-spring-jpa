package org.sg.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

import static org.junit.Assert.*;
import static org.sg.test.util.EntityUtils.*;

public class ExamServiceTest extends AbstractSpringTest {

	private static Logger logger = LogManager.getLogger(ExamServiceTest.class);

	@Autowired
	private ExamService examService;

	@Test
	public void test_getExam_fromStudent() {
		//Given
		final Integer studentId = 1;

		//When
		StudentEntity studentEntity = examService.getStudent(studentId);

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
		CourseEntity courseEntity = examService.getCourse(courseId);;

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
	public void test_insertExam_toStudent_forNewCourse() throws ParseException {
		//Given
		final Integer studentId = 6;

		//When
		StudentEntity studentEntity = examService.getStudent(studentId);
		CourseEntity courseEntity = createCourse();
		examService.insertCourse(courseEntity);
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		studentEntity.addExam(examEntity);
		examService.updateStudent(studentEntity);
		StudentEntity studentEntityDb = examService.getStudent(studentId);

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
		StudentEntity studentEntity = examService.getStudent(studentId);
		CourseEntity courseEntity = examService.getCourse(courseId);;
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		studentEntity.addExam(examEntity);
		examService.updateStudent(studentEntity);
		StudentEntity studentEntityDb = examService.getStudent(studentId);

		//Then
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
	}

	@Test
	public void test_updateExam() throws ParseException {
		//Given
		final Integer studentId = 2;
		final Integer examId = 2;

		//When
		StudentEntity studentEntity = examService.getStudent(studentId);
		ExamEntity examEntity = studentEntity.getExamById(examId);
		examEntity = updateExam(examEntity);
		examService.updateStudent(studentEntity);
		StudentEntity studentEntityDb = examService.getStudent(studentId);

		//Then
		assertEquals(studentEntityDb.getExamById(examId), studentEntity.getExamById(examId));
	}

	@Test
	public void test_deleteExam() {
		//Given
		final Integer studentId = 3;
		final Integer examId = 4;

		//When
		StudentEntity studentEntity = examService.getStudent(studentId);
		studentEntity.removeExamById(examId);
		examService.updateStudent(studentEntity);
		StudentEntity studentEntityDb = examService.getStudent(studentId);

		//Then
		assertNull(studentEntityDb.getExamById(examId));
	}

	@Test
	public void test_delete_allExams() {
		//Given
		final Integer studentId = 4;

		//When
		StudentEntity studentEntity = examService.getStudent(studentId);
		studentEntity.getExams().clear();
		examService.updateStudent(studentEntity);
		StudentEntity studentEntityDb = examService.getStudent(studentId);

		//Then
		assertNotNull(studentEntityDb);
		assertEquals(0, studentEntityDb.getExams().size());
	}

	@Test
	public void test_delete_student_withExams() {
		//Given
		final Integer studentId = 5;

		//When
		boolean deleting = examService.deleteStudent(studentId);
		StudentEntity studentEntityDb = examService.getStudent(studentId);

		//Then
		assertTrue(deleting);
		assertNull(studentEntityDb);
	}

	@Test
	public void test_CRUD_exam() throws ParseException {
		// 1. insert a new student
		StudentEntity studentEntity = createStudent();
		examService.insertStudent(studentEntity);
		assertNotNull(studentEntity.getId());

		// 2. insert a new course
		CourseEntity courseEntity = createCourse();
		examService.insertCourse(courseEntity);
		assertNotNull(courseEntity.getId());

		// 3. insert an exam for the student
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		assertNotNull(examEntity);
		examService.updateStudent(studentEntity);

		// 4. Get and check if the student and the exam has correctly been fetched
		StudentEntity studentEntityDb = examService.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertNotNull(studentEntityDb.getExams());
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());

		// 5. Update the exam, and Get to check if updated correctly
		examEntity = updateExam(examEntity);
		examService.updateStudent(studentEntity);
		studentEntityDb = examService.getStudent(studentEntity.getId());
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());

		// 6. Delete the exam, and Get to check if is deleted correctly
		studentEntity.removeExamById(examEntity.getId());
		examService.updateStudent(studentEntity);
		studentEntityDb = examService.getStudent(studentEntity.getId());
		assertNull(studentEntityDb.getExamById(examEntity.getId()));
	}
}