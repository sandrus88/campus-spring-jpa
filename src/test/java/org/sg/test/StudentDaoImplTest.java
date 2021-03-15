package org.sg.test;

import org.junit.Assert;
import org.junit.Test;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.StudentEntity;

public class StudentDaoImplTest {
	
	StudentDao studentDao = new StudentDaoImpl();
	
	@Test
	public void insertStudent() {
	    StudentEntity studentEntity = new StudentEntity();
	    studentEntity.setName("Manuel");
	    studentEntity.setSurname("Castro");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Confirmed");
	    studentEntity.setSex('M');
	    
	    StudentEntity studentEntity2 = new StudentEntity();
	    studentEntity2.setName("Manuel2");
	    studentEntity2.setSurname("Castro2");
	    studentEntity2.setJobTitle("Waiter2");
	    studentEntity2.setPaymentType("Confirmed2");
	    studentEntity2.setSex('M');
	    
	    StudentEntity studentEntity3 = new StudentEntity();
	    studentEntity3.setName("Manuel3");
	    studentEntity3.setSurname("Castro3");
	    studentEntity3.setJobTitle("Waiter3");
	    studentEntity3.setPaymentType("Confirmed3");
	    studentEntity3.setSex('M');
	    
	    studentEntity = studentDao.createStudent(studentEntity);
	    studentEntity2 = studentDao.createStudent(studentEntity2);
	    studentEntity3 = studentDao.createStudent(studentEntity3);
	    System.out.println(studentEntity);
	    System.out.println(studentEntity2);
	    System.out.println(studentEntity3);
	    
	    Assert.assertEquals(studentEntity.getId(), studentEntity.getId());
	    Assert.assertEquals(studentEntity2.getId(), studentEntity2.getId());
	    Assert.assertEquals(studentEntity3.getId(), studentEntity3.getId());
	}
	
	@Test
	public void searchStudentById() {
	    StudentEntity studentEntity = studentDao.get(1);
	    System.out.println(studentEntity);
	    Assert.assertEquals(studentEntity.getName(), "Manuel");
	    StudentEntity studentEntity2 = studentDao.get(2);
	    System.out.println(studentEntity2);
	    Assert.assertEquals(studentEntity2.getName(), "Manuel2");
	    StudentEntity studentEntity3 = studentDao.get(3);
	    System.out.println(studentEntity3);
	    Assert.assertEquals(studentEntity3.getName(), "Manuel3");
	    
	}
	
	@Test
	public void updateStudentFromDatabase() {
	    StudentEntity studentEntity = studentDao.get(1);
	    System.out.println(studentEntity);
	    studentEntity.setName("Sandro");
	    studentEntity.setSurname("Gargano");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Not Confirmed");
	    studentEntity.setSex('M');
	    
	    studentEntity = studentDao.update(studentEntity);
	    System.out.println(studentEntity);
	    Assert.assertEquals(studentEntity.getSurname(), "Gargano");
	}
	
	@Test
	public void deleteStudentById() {
	    boolean deleting = studentDao.delete(1);
	    StudentEntity studentEntity = studentDao.get(1);
	    System.out.println("Dopo l'eliminazione" + studentEntity);
	    Assert.assertTrue(deleting);
	    Assert.assertNull(studentEntity);
	    
	}
}
