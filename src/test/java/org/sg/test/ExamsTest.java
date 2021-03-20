package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class ExamsTest {

	public static Logger logger = LogManager.getLogger(ExamsTest.class);
	StudentDao studentDao = new StudentDaoImpl();
	CourseDao courseDao = new CourseDaoImpl();

	@Test
	public void test_get_exams_fromStudent() {
		final Integer studentId = 1;
		StudentEntity studentEntity = studentDao.get(studentId);
		logger.info(studentEntity);
		assertNotNull(studentEntity);

		List<ExamEntity> exams = studentEntity.getExams();
		logger.info(exams);
		assertNotNull(exams);

		assertEquals(exams.get(0).getId(), "Exm 009");
		assertEquals(exams.get(1).getId(), "Exm 010");
		assertEquals(exams.get(2).getId(), "Exm 013");
		assertEquals(exams.get(3).getId(), "Exm 014");
	}

	@Test
	public void test_get_exams_fromCourse() {
		final Integer courseId = 201;
		CourseEntity courseEntity = courseDao.get(courseId);
		logger.info(courseEntity);
		assertNotNull(courseEntity);

		List<ExamEntity> exams = courseEntity.getExams();
		logger.info(exams);
		assertNotNull(exams);

		assertEquals(exams.get(0).getId(), "Exm 001");
		assertEquals(exams.get(1).getId(), "Exm 002");
		assertEquals(exams.get(2).getId(), "Exm 004");
		assertEquals(exams.get(3).getId(), "Exm 012");
		assertEquals(exams.get(4).getId(), "Exm 013");
	}
	
	@Test
	public void test_insert_exam_toStudent_forNewCourse() throws ParseException {
		final Integer studentId = 6;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		logger.info(studentEntity);
		CourseEntity newCourse = new CourseEntity();
		newCourse.setName("New Course");
		newCourse.setDescription("Description of new course");
		
		newCourse = courseDao.insert(newCourse);
		logger.info("Nuovo corso inserito " + newCourse);
		assertNotNull(newCourse.getId());
		
		List<ExamEntity> examsList = new ArrayList<>();
		ExamEntity exam = new ExamEntity();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("14/12/2021");
		
		exam.setId("Exm Added For New Course");
		exam.setExamDate(date);
		exam.setMark(18);
		exam.setCourseEntity(newCourse);
		exam.setStudentEntity(studentEntity);
		examsList.add(exam);
		studentEntity.setExams(examsList);
		
		studentDao.update(studentEntity);
		logger.info("Esami inseriti allo studente: " + exam);

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
	}
	
	@Test
	public void test_insert_exam_toStudent_forExistingCourse() throws ParseException {
		final Integer studentId = 7;
		final Integer courseId = 201;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		logger.info(studentEntity);
		CourseEntity courseEntity = courseDao.get(courseId);
		assertNotNull(courseEntity);
		logger.info(courseEntity);
		List<ExamEntity> examsList = new ArrayList<>();
		ExamEntity exam = new ExamEntity();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("29/11/2020");
		
		exam.setId("Exm Added For Existing Course");
		exam.setExamDate(date);
		exam.setMark(22);
		exam.setCourseEntity(courseEntity);
		exam.setStudentEntity(studentEntity);
		courseDao.update(courseEntity);
		examsList.add(exam);
		studentEntity.setExams(examsList);
		
		studentDao.update(studentEntity);
		logger.info("Esami inseriti allo studente: " + exam);

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
	}
}