package org.sg.test;

import org.junit.Assert;
import org.junit.Test;
import org.sg.entities.StudentEntity;
import org.sg.services.StudentService;

public class TestJpa {
	
	@Test
	public void insertStudent() {
	    StudentService crud = new StudentService();

	    StudentEntity studentEntity = new StudentEntity();
	    studentEntity.setName("Manuel");
	    studentEntity.setSurname("Castro");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Confirmed");
	    studentEntity.setSex('M');
	    
	    studentEntity = crud.saveStudent(studentEntity);

	    Assert.assertEquals(studentEntity.getId(), 1);
	}
}
