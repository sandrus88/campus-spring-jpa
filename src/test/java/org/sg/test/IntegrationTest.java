package org.sg.test;

import org.junit.Test;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.StudentEntity;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createStudent;
import static org.sg.test.util.EntityUtils.updateStudent;

public class IntegrationTest {
	public StudentDaoImpl crud = new StudentDaoImpl();
	
	@Test
	public void test_CRUD_student() {
		// 1. insert a new student in DB
		StudentEntity studentEntity = createStudent();
	    studentEntity = crud.insert(studentEntity);
		assertNotNull(studentEntity.getId());
		
		// 2. get the student from DB
		StudentEntity studentEntityDb = crud.get(studentEntity.getId());
	    assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb, studentEntity);
		
		// 3. Update the student in DB, and Get to check if updated correctly
		studentEntity = updateStudent();
	    crud.update(studentEntity);
		studentEntityDb = crud.get(studentEntity.getId());
		assertEquals(studentEntityDb, studentEntity);
	 
		// 4. Delete the student from DB, and Get to check if deleted correctly
	    boolean isRemoved = crud.delete(studentEntity.getId());
	    assertTrue(isRemoved);
		studentEntityDb = crud.get(studentEntity.getId());
	    assertNull(studentEntityDb);
	}
	
	@Test
	public void test_CRUD_address() {
		// 1. insert a new student
		
		// 2. insert an address for the student
		
		// 3. Get and check if the student and the address has correctly been fetched
		
		// 4. Update the address, and Get to check if is updated correctly
		
		// 5. Delete the address of the student, and Get to check if is deleted correctly
		
	}
	
	@Test
	public void test_CRUD_studentWithAddress() {
		// 1. insert a new student with address
		
		// 2. Get and check if the student and the address has correctly been fetched
		
		// 3. Delete the student, and Get to check if is deleted correctly
	}
	

	
}
