package org.sg.test;

import org.junit.Assert;
import org.junit.Test;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.StudentEntity;

public class IntegrationTest {
	public StudentDaoImpl crud = new StudentDaoImpl();
	public boolean isRemoved;
	
	@Test
	public void test_CRUD() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName("Manuel");
	    studentEntity.setSurname("Castro");
	    studentEntity.setJobTitle("Waiter");
	    studentEntity.setPaymentType("Confirmed");
	    studentEntity.setSex('M');
	    
	    studentEntity = crud.insert(studentEntity);
	    Assert.assertEquals(studentEntity.getId(), studentEntity.getId());
	    
	    studentEntity = crud.get(1001);
	    Assert.assertNotNull(studentEntity);
	    
	    studentEntity.setName("Sandro");
	    studentEntity.setSurname("Gargano");
	    studentEntity = crud.update(studentEntity);
	    
	    studentEntity = crud.get(1001);
	    Assert.assertEquals(studentEntity.getName(), "Sandro");
	    Assert.assertEquals(studentEntity.getSurname(), "Gargano");
	    
	    isRemoved = crud.delete(1001);
	    Assert.assertNull(studentEntity);	
	}
}
