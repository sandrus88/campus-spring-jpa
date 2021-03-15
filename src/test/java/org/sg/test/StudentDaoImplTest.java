package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.StudentEntity;

public class StudentDaoImplTest {
	
	StudentDao studentDao = new StudentDaoImpl();
	
	@Test
	public void test_insert() {
	    StudentEntity studentEntity = new StudentEntity();
	    studentEntity.setName("Manuel");
	    studentEntity.setSurname("Castro");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Confirmed");
	    studentEntity.setSex('M');
	    System.out.println("Studente da inserire: " + studentEntity);
	    
	    studentEntity = studentDao.insert(studentEntity);
	    System.out.println("Studente inserito: " + studentEntity);
	    assertNotNull(studentEntity.getId()); 
	    
	    StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
	    System.out.println("Studente nel db: " + studentEntityDb);
	    assertNotNull(studentEntityDb);
	    assertEquals(studentEntityDb.getName(), studentEntity.getName());
	    assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
	    assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
	    assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
	    assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	}
	
	@Test
	public void test_get() {
		final Integer studentId = 6;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    System.out.println(studentEntity);
	    assertNotNull(studentEntity);
	    assertEquals(studentEntity.getName(), "Aida");
	    assertEquals(studentEntity.getSurname(), "Xhaxho");
	    assertEquals(studentEntity.getJobTitle(), "Beauty Consultant");
	    assertEquals(studentEntity.getPaymentType(), "Not confirmed");
	    assertEquals(studentEntity.getSex(), Character.valueOf('F'));
	}
	
	@Test
	public void test_get_withAddress() {
		final Integer studentId = 3;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    System.out.println(studentEntity);
	    assertNotNull(studentEntity);
	    assertEquals(studentEntity.getName(), "Aida");
	    assertEquals(studentEntity.getSurname(), "Xhaxho");
	    assertEquals(studentEntity.getJobTitle(), "Beauty Consultant");
	    assertEquals(studentEntity.getPaymentType(), "Not confirmed");
	    assertEquals(studentEntity.getSex(), Character.valueOf('F'));
	}
	
	@Test
	public void test_get_notPresent() {
		final Integer studentId = -1;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    System.out.println(studentEntity);
	    assertNull(studentEntity);
	}
	// adattala come gli altri studentisd 6
	// get student 6, verifico che no sia null
	// cambia tuttu ic campi 
	// salva
	// get dal db 
	// vrifica che i campi sono quelli cambiati, doppo aver assertato che non sia null
	@Test
	public void test_update() {
	    StudentEntity studentEntity = studentDao.get(1016);
	    System.out.println("Prima dell'update " + studentEntity);
	    studentEntity.setName("Sandro");
	    studentEntity.setSurname("Gargano");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Not Confirmed");
	    studentEntity.setSex('M');
	    
	    studentEntity = studentDao.update(studentEntity);
	    System.out.println("Dopo l'update" + studentEntity);
	    assertEquals(studentEntity.getSurname(), "Gargano");
	}
	
	@Test
	public void test_delete() {
		final Integer studentId = 6;
		
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		
	    boolean deleting = studentDao.delete(studentId);
	    studentEntity = studentDao.get(studentId);
	    System.out.println("Dopo l'eliminazione " + studentEntity);
	    assertTrue(deleting);
	    assertNull(studentEntity);    
	}
	
	@Test
	public void test_delete_notPresent() {
		final Integer studentId = -1;
	    boolean deleting = studentDao.delete(studentId);
	    assertFalse(deleting);   
	}
	
	// how jps-hibernate resolve 1:1 relationship in case of delete
	@Test
	public void test_delete_WithAddress() {
		final Integer studentId = 3;
		
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		
	    boolean deleting = studentDao.delete(studentId);
	    System.out.println("Dopo l'eliminazione " + studentEntity);
	    assertTrue(deleting);
	    assertNull(studentEntity);
	    
	}
}
