package org.sg.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getExams());
		logger.info("Esami dello studente con id: " + studentEntity.getId() + " sono: " + studentEntity.getExams());

		assertEquals(studentEntity.getExams().get(0).getId(), Integer.valueOf(409));
		assertEquals(studentEntity.getExams().get(1).getId(), Integer.valueOf(410));
		assertEquals(studentEntity.getExams().get(2).getId(), Integer.valueOf(413));
		assertEquals(studentEntity.getExams().get(3).getId(), Integer.valueOf(414));
	}

	@Test
	public void test_get_exams_fromCourse() {
		final Integer courseId = 201;
		CourseEntity courseEntity = courseDao.get(courseId);
		assertNotNull(courseEntity);
		assertNotNull(courseEntity.getExams());
		logger.info("Esami dello studente con id: " + courseEntity.getId() + " sono: " + courseEntity.getExams());

		assertEquals(courseEntity.getExams().get(0).getId(), Integer.valueOf(402));
		assertEquals(courseEntity.getExams().get(1).getId(), Integer.valueOf(401));
		assertEquals(courseEntity.getExams().get(2).getId(), Integer.valueOf(412));
		assertEquals(courseEntity.getExams().get(3).getId(), Integer.valueOf(413));
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
		final Integer courseId = 206;
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
	
	@Test
	public void test_update_exam() {
		final Integer studentId = 2;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		logger.info(studentEntity);
		
		List<ExamEntity> examsList = studentEntity.getExams();
		logger.info("Esami dello studente: " + examsList);
		ExamEntity exam = examsList.get(0);
		
		exam.setMark(14);
		
		studentDao.update(studentEntity);
		logger.info("Voto dell'esame aggiornato: " + exam.getMark());

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getExams().get(0).getMark(), studentEntity.getExams().get(0).getMark());
	}
	
	@Test
	public void test_delete_only_one_exam() {
		final Integer studentId = 3;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getExams());
		logger.info("Esami dello studente con id: " + studentEntity.getId() + " sono: " + studentEntity.getExams());
		
		studentEntity.getExams().remove(0); 
		studentDao.update(studentEntity);
		assertNotNull(studentEntity.getExams());
		logger.info("Esami dopo l'eliminazione sono: " + studentEntity.getExams());

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getExams().get(0).getId(), Integer.valueOf(405));
	}
	
	@Test
	public void test_delete_all_exams() {
		final Integer studentId = 4;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getExams());
		logger.info("Esami dello studente con id: " + studentEntity.getId() + " sono: " + studentEntity.getExams());
		
		studentEntity.getExams().clear();
		studentDao.update(studentEntity);

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertTrue(studentEntityDb.getExams().isEmpty());
	}
	
	@Test
	public void test_delete_student_withExams() {
		final Integer studentId = 5;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getExams());
		logger.info("Esami dello studente con id: " + studentEntity.getId() + " sono: " + studentEntity.getExams());
		
		boolean deleting = studentDao.delete(studentId);
		assertTrue(deleting);

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		assertNull(studentEntityDb);
	}
}