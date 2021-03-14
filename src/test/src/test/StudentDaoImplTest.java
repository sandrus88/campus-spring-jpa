package org.sg.test;

import org.junit.Assert;
import org.junit.Test;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.StudentEntity;

public class StudentDaoImplTest {
	
	@Test
	public void insertStudent() {
	    StudentDaoImpl crud = new StudentDaoImpl();

	    StudentEntity studentEntity = new StudentEntity();
	    studentEntity.setName("Manuel");
	    studentEntity.setSurname("Castro");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Confirmed");
	    studentEntity.setSex('M');
	    
	    studentEntity = crud.createStudent(studentEntity);

	    Assert.assertEquals(studentEntity.getId(), 1);
	}
}
