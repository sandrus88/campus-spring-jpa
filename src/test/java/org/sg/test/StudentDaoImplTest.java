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
import org.sg.dao.StudentDao;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;

public class StudentDaoImplTest {
	
	private static Logger logger = LogManager.getLogger(StudentDaoImplTest.class);
	private StudentDao studentDao = new StudentDaoImpl();
	
	@Test
	public void test_get_withoutAddress() {
		//Given
		final Integer studentId = 21;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		
		//Then
		assertNotNull(studentEntity);
		assertEquals(studentEntity.getName(), "Armela");
		assertEquals(studentEntity.getSurname(), "Xhaxho");
		assertEquals(studentEntity.getJobTitle(), "Shop Assistant");
		assertEquals(studentEntity.getPaymentType(), "To be confirmed");
		assertEquals(studentEntity.getSex(), Character.valueOf('F'));
	}

	@Test
	public void test_get_withAddress() {
		//Given
		final Integer studentId = 1;
		final Integer addressId = 1;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		
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
	public void test_get_notPresent() {
		//Given
		final Integer studentId = -1;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		
		//Then
		assertNull(studentEntity);
	}

	@Test
	public void test_insert() {
		//Given
		StudentEntity studentEntity = createStudent();
		
		//When
		studentDao.insert(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
		assertNull(studentEntityDb.getAddressEntity());
	}

	@Test
	public void test_insert_withAddress() {
		//Given
		StudentEntity studentEntity = createStudentWithAddress();
		
		//When
		studentDao.insert(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
	}

	@Test
	public void test_update_withoutAddress() {
		//Given
		final Integer studentId = 22;
		
		//When
		StudentEntity studentEntity = updateStudent(studentDao.get(studentId));
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
	}
	
	@Test
	public void test_add_address_for_existingStudent() {
		//Given
		final Integer studentId = 23;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		AddressEntity addressEntity = createAddress(studentEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertNotNull(studentEntityDb.getAddressEntity());
		assertEquals(studentEntityDb.getAddressEntity(), addressEntity);
	}
	
	@Test
	public void test_update_student_withAddress_addressShouldNotChange() {
		//Given
		final Integer studentId = 2;
		
		//When
		StudentEntity studentEntity = updateStudent(studentDao.get(studentId));
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertEquals(studentEntityDb, studentEntity);
	}

	@Test
	public void test_update_address_for_existingStudent() {
		//Given
		final Integer studentId = 3;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		AddressEntity addressEntity = updateAddress(studentEntity.getAddressEntity());
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertEquals(studentEntityDb.getAddressEntity(), addressEntity);
	}

	@Test
	public void test_delete() {
		//Given
		final Integer studentId = 24;
		
		//When
		boolean deleting = studentDao.delete(studentId);
		StudentEntity studentEntity = studentDao.get(studentId);
		
		//Then
		assertTrue(deleting);
		assertNull(studentEntity);
	}

	@Test
	public void test_delete_notPresent() {
		//Given
		final Integer studentId = -1;
		
		//When
		boolean deleting = studentDao.delete(studentId);
		
		//Then
		assertFalse(deleting);
	}

	@Test
	public void test_delete_WithAddress() {
		//Given
		final Integer studentId = 4;
		
		//When
		boolean deleting = studentDao.delete(studentId);
		StudentEntity studentEntity = studentDao.get(studentId);
		
		//Then
		assertTrue(deleting);
		assertNull(studentEntity);
	}
	
	@Test
	public void test_delete_address() {
		//Given
		final Integer studentId = 5;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		studentEntity.getAddressEntity().setStudentEntity(null);
		studentEntity.setAddressEntity(null);
		studentDao.update(studentEntity);
		StudentEntity db = studentDao.get(studentEntity.getId());
		
		//Then
		assertNull(db.getAddressEntity());
	}
}
