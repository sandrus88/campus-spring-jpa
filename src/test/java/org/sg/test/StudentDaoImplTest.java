package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.AddressEntity;
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
	public void test_insert_withAddress() {
	    StudentEntity studentEntity = new StudentEntity();
	    studentEntity.setName("Manuel");
	    studentEntity.setSurname("Castro");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Confirmed");
	    studentEntity.setSex('M');
	    System.out.println("Studente da inserire: " + studentEntity);
	    
	    studentEntity = studentDao.insert(studentEntity);
	    
	    AddressEntity addressEntity = new AddressEntity();
	    addressEntity.setId(studentEntity.getId());
	    addressEntity.setStreet("Via del pentimento");
	    addressEntity.setBuildingNumber("30");
	    addressEntity.setPostalCode(50122);
	    addressEntity.setCity("Firenze");
	    addressEntity.setProvinceCode("FI");
	    studentEntity.setAddressEntity(addressEntity);
	    System.out.println("Studente inserito con indirizzo: " + studentEntity);
	    assertNotNull(studentEntity.getId()); 
	    assertNotNull(addressEntity.getId());
	    
	    StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
	    System.out.println("Studente nel db: " + studentEntityDb);
	    assertNotNull(studentEntityDb);
	    assertEquals(studentEntityDb.getName(), studentEntity.getName());
	    assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
	    assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
	    assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
	    assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	    assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
	}
	
	@Test
	public void test_get() {
		final Integer studentId = 6;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    System.out.println(studentEntity);
	    
	    assertNotNull(studentEntity);
	    assertEquals(studentEntity.getName(), "Andrea");
	    assertEquals(studentEntity.getSurname(), "Bonfanti");
	    assertEquals(studentEntity.getJobTitle(), "Engineer");
	    assertEquals(studentEntity.getPaymentType(), "To be confirmed");
	    assertEquals(studentEntity.getSex(), Character.valueOf('M'));
	}
	
	@Test
	public void test_get_withAddress() {
		final Integer studentId = 3;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    AddressEntity addressEntity = studentEntity.getAddressEntity();
	    System.out.println(studentEntity);
	    
	    assertNotNull(studentEntity);
	    assertNotNull(addressEntity);
	    assertEquals(studentEntity.getName(), "Ermal");
	    assertEquals(studentEntity.getSurname(), "Aliraj");
	    assertEquals(studentEntity.getJobTitle(), "Web Developer");
	    assertEquals(studentEntity.getPaymentType(), "Confirmed");
	    assertEquals(studentEntity.getSex(), Character.valueOf('M'));
	    assertEquals(addressEntity.getId(), studentEntity.getId());    
	}
	
	@Test
	public void test_get_notPresent() {
		final Integer studentId = -1;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    System.out.println(studentEntity);
	    assertNull(studentEntity);
	}
	
	@Test
	public void test_update() {
		final Integer studentId = 6;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    System.out.println("Prima dell'update " + studentEntity);
	    assertNotNull(studentEntity);
	  
	    studentEntity.setName("Mirella");
	    studentEntity.setSurname("Gangitano");
	    studentEntity.setJobTitle("Teacher");
	    studentEntity.setPaymentType("Not Confirmed");
	    studentEntity.setSex('F');
	    
	    studentEntity = studentDao.update(studentEntity);
	    System.out.println("Dopo l'update" + studentEntity);
	    assertNotNull(studentEntity);
	    
	    StudentEntity studentEntityDb = studentDao.get(studentId);
	    System.out.println("Studente nel db: " + studentEntityDb);
	    assertNotNull(studentEntityDb);
	    assertEquals(studentEntityDb.getName(), studentEntity.getName());
	    assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
	    assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
	    assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
	    assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	}
	
	@Test
	public void test_update_withAddress() {
		final Integer studentId = 6;
	    StudentEntity studentEntity = studentDao.get(studentId);
	    AddressEntity addressEntity = studentEntity.getAddressEntity();
	    System.out.println("Prima dell'update " + studentEntity);
	    assertNotNull(studentEntity);
	    assertNotNull(addressEntity);
	    
	    studentEntity.setName("Mirella");
	    studentEntity.setSurname("Gangitano");
	    studentEntity.setJobTitle("Teacher");
	    studentEntity.setPaymentType("Not Confirmed");
	    studentEntity.setSex('F');
	    addressEntity.setStreet("Via Panormus");
	    addressEntity.setBuildingNumber("15/F");
	    addressEntity.setPostalCode(90100);
	    addressEntity.setCity("Palermo");
	    addressEntity.setProvinceCode("PA");
	    studentEntity.setAddressEntity(addressEntity);
	    
	    studentEntity = studentDao.update(studentEntity);
	    System.out.println("Dopo l'update" + studentEntity);
	    assertNotNull(studentEntity);
	    assertNotNull(addressEntity);
	    
	    StudentEntity studentEntityDb = studentDao.get(studentId);
	    System.out.println("Studente nel db: " + studentEntityDb);
	    assertNotNull(studentEntityDb);
	    assertEquals(studentEntityDb.getName(), studentEntity.getName());
	    assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
	    assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
	    assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
	    assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	    assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
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
