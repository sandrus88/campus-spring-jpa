package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.sg.test.util.EntityUtils.createCourse;
import static org.sg.test.util.EntityUtils.createExam;
import static org.sg.test.util.EntityUtils.createStudent;
import static org.sg.test.util.EntityUtils.updateExam;

import java.text.ParseException;

import org.junit.Test;
import org.sg.dao.CourseDao;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;

public class ExamDao_IntegrationTest {
	private CourseDao crudCourse = new CourseDaoImpl();
	private StudentDao crudStudent = new StudentDaoImpl();
	
	@Test
	public void test_CRUD_exam() throws ParseException {
		// 1. insert a new student
		StudentEntity studentEntity = createStudent();
		crudStudent.insert(studentEntity);
		assertNotNull(studentEntity.getId());
		
		// 2. insert a new course
		CourseEntity courseEntity = createCourse();
		crudCourse.insert(courseEntity);
		assertNotNull(courseEntity.getId());
		
		// 3. insert an exam for the student
		ExamEntity examEntity = createExam(studentEntity, courseEntity);
		assertNotNull(examEntity);
		crudStudent.update(studentEntity);
		
		// 4. Get and check if the student and the exam has correctly been fetched
		StudentEntity studentEntityDb = crudStudent.get(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertNotNull(studentEntityDb.getExams());
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
		
		// 5. Update the exam, and Get to check if updated correctly
		updateExam(examEntity);
		crudStudent.update(studentEntity);
		studentEntityDb = crudStudent.get(studentEntity.getId());
		assertEquals(studentEntityDb.getExams(), studentEntity.getExams());
		
		// 6. Delete the exam, and Get to check if is deleted correctly
		studentEntity.removeExamById(examEntity.getId()); 
		crudStudent.update(studentEntity);
		studentEntityDb = crudStudent.get(studentEntity.getId());
		assertNull(studentEntityDb.getExamById(examEntity.getId()));
	}
}
