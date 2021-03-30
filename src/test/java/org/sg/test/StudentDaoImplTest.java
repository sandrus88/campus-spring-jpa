package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createAddress;
import static org.sg.test.util.EntityUtils.createStudent;
import static org.sg.test.util.EntityUtils.createStudentWithAddress;
import static org.sg.test.util.EntityUtils.updateAddress;
import static org.sg.test.util.EntityUtils.updateStudent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDaoImplTest {
	
	private static Logger logger = LogManager.getLogger(StudentDaoImplTest.class);
	
	@Autowired
	private StudentService studentDao;
	
	@Test
	public void test_getStudent_withoutAddress() {
		//Given
		final Integer studentId = 21;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		
		//Then
		assertNotNull(studentEntity);
		assertEquals(studentEntity.getName(), "Armela");
		assertEquals(studentEntity.getSurname(), "Xhaxho");
		assertEquals(studentEntity.getJobTitle(), "Shop Assistant");
		assertEquals(studentEntity.getPaymentType(), "To be confirmed");
		assertEquals(studentEntity.getSex(), Character.valueOf('F'));
	}

	@Test
	public void test_getStudent_withAddress() {
		//Given
		final Integer studentId = 1;
		final Integer addressId = 1;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		
		//Then
		assertEquals(studentEntity.getName(), "Sandro");
		assertEquals(studentEntity.getSurname(), "Gargano");
		assertEquals(studentEntity.getJobTitle(), "Waiter");
		assertEquals(studentEntity.getPaymentType(), "Confirmed");
		assertEquals(studentEntity.getSex(), Character.valueOf('M'));
		assertNotNull(studentEntity.getAddressEntity());
		assertEquals(studentEntity.getAddressEntity().getId(), addressId);
	}

	@Test
	public void test_getStudent_notPresent() {
		//Given
		final Integer studentId = -1;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		
		//Then
		assertNull(studentEntity);
	}

	@Test
	public void test_insertStudent_withoutAddress() {
		//Given
		StudentEntity studentEntity = createStudent();
		
		//When
		studentDao.insert(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentEntity.getId());
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
		assertNull(studentEntityDb.getAddressEntity());
	}

	@Test
	public void test_insertStudent_withAddress() {
		//Given
		StudentEntity studentEntity = createStudentWithAddress();
		
		//When
		studentDao.insert(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentEntity.getId());
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
	}
	
	@Test
	public void test_updateStudent_withoutAddress() {
		//Given
		final Integer studentId = 22;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		studentEntity = updateStudent(studentEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
	}
	
	@Test
	public void test_updateStudent_withAddress() {
		//Given
		final Integer studentId = 2;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		studentEntity = updateStudent(studentEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
	}
	
	@Test
	public void test_deleteStudent_withoutAddress() {
		//Given
		final Integer studentId = 24;
		
		//When
		boolean deleting = studentDao.deleteStudent(studentId);
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		
		//Then
		assertTrue(deleting);
		assertNull(studentEntity);
	}
	
	@Test
	public void test_deleteStudent_withAddress() {
		//Given
		final Integer studentId = 4;
		
		//When
		boolean deleting = studentDao.deleteStudent(studentId);
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		
		//Then
		assertTrue(deleting);
		assertNull(studentEntity);
	}
	
	@Test
	public void test_deleteStudent_notPresent() {
		//Given
		final Integer studentId = -1;
		
		//When
		boolean deleting = studentDao.deleteStudent(studentId);
		
		//Then
		assertFalse(deleting);
	}
	
	@Test
	public void test_addAddress_forStudent() {
		//Given
		final Integer studentId = 23;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		AddressEntity addressEntity = createAddress(studentEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);
		
		//Then
		assertNotNull(studentEntityDb.getAddressEntity());
		assertEquals(studentEntityDb.getAddressEntity(), addressEntity);
	}
	
	@Test
	public void test_deleteAddress_forStudent() {
		//Given
		final Integer studentId = 5;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		studentEntity.getAddressEntity().setStudentEntity(null);
		studentEntity.setAddressEntity(null);
		studentDao.update(studentEntity);
		StudentEntity db = studentDao.getStudent(studentEntity.getId());
		
		//Then
		assertNull(db.getAddressEntity());
	}
	
	@Test
	public void test_updateAddress_forStudent() {
		//Given
		final Integer studentId = 3;
		
		//When
		StudentEntity studentEntity = studentDao.getStudent(studentId);
		AddressEntity addressEntity = updateAddress(studentEntity.getAddressEntity());
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.getStudent(studentId);
		
		//Then
		assertEquals(studentEntityDb.getAddressEntity(), addressEntity);
	}
	
	@Test
	public void test_student_integrationTest_CRUD() {
		// 1. insert a new student in DB
		StudentEntity studentEntity = createStudent();
		studentDao.insert(studentEntity);
		assertNotNull(studentEntity.getId());
		
		// 2. get the student from DB
		StudentEntity studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb, studentEntity);
		
		// 3. Update the student in DB, and Get to check if updated correctly
		updateStudent(studentEntity);
		studentDao.update(studentEntity);
		studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertEquals(studentEntityDb, studentEntity);
		
		// 4. Delete the student from DB, and Get to check if deleted correctly
		boolean isRemoved = studentDao.deleteStudent(studentEntity.getId());
		assertTrue(isRemoved);
		studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertNull(studentEntityDb);
	}
	
	@Test
	public void test_address_integrationTest_CRUD() {
		// 1. insert a new student
		StudentEntity studentEntity = createStudent();
		studentDao.insert(studentEntity);
		assertNotNull(studentEntity.getId());
		
		// 2. insert an address for the student
		AddressEntity addressEntity = createAddress(studentEntity);
		assertNotNull(addressEntity);
		studentDao.update(studentEntity);
		
		// 3. Get and check if the student and the address has correctly been fetched
		StudentEntity studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertNotNull(studentEntityDb.getAddressEntity());
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
		
		// 4. Update the address, and Get to check if is updated correctly
		updateAddress(addressEntity);
		studentDao.update(studentEntity);
		studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
		
		// 5. Delete the address of the student, and Get to check if is deleted
		// correctly
		studentEntity.getAddressEntity().setStudentEntity(null);
		studentEntity.setAddressEntity(null);
		studentDao.update(studentEntity);
		studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertNull(studentEntityDb.getAddressEntity());
	}
	
	@Test
	public void test_studentWithAddress_integrationTest_CRUD() {
		// 1. insert a new student with address
		StudentEntity studentEntity = createStudentWithAddress();
		studentDao.insert(studentEntity);
		assertNotNull(studentEntity.getId());
		assertNotNull(studentEntity.getAddressEntity());
		
		// 2. Get and check if the student and the address has correctly been fetched
		StudentEntity studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertNotNull(studentEntityDb.getAddressEntity());
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
		
		// 3. Delete the student, and Get to check if is deleted correctly
		boolean isRemoved = studentDao.deleteStudent(studentEntity.getId());
		assertTrue(isRemoved);
		studentEntityDb = studentDao.getStudent(studentEntity.getId());
		assertNull(studentEntityDb);
	}
	
}
