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
	    System.out.println(studentEntity);
	    Assert.assertEquals(studentEntity.getId(), Integer.valueOf(1));
	}
	
	@Test
	public void searchStudentById() {
	    StudentDaoImpl crud = new StudentDaoImpl();

	    StudentEntity studentEntity = crud.get(Integer.valueOf(1));
	    System.out.println(studentEntity);
	    Assert.assertEquals(studentEntity.getName(), "Manuel");
	}
	
	@Test
	public void updateStudentFromDatabase() {
	    StudentDaoImpl crud = new StudentDaoImpl();

	    StudentEntity studentEntity = crud.get(Integer.valueOf(1));
	    System.out.println(studentEntity);
	    studentEntity.setName("Sandro");
	    studentEntity.setSurname("Gargano");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Not Confirmed");
	    studentEntity.setSex('M');
	    
	    studentEntity = crud.update(studentEntity);
	    System.out.println(studentEntity);
	    Assert.assertEquals(studentEntity.getSurname(), "Gargano");
	}
	
	@Test
	public void deleteStudentById() {
	    StudentDaoImpl crud = new StudentDaoImpl();
	    
	    boolean deleting = crud.delete(Integer.valueOf(1));
	    Assert.assertTrue(deleting);
	}
}
