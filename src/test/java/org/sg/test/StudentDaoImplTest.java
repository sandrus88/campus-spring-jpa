package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createStudentWithAddress;
import static org.sg.test.util.EntityUtils.createStudent;
import static org.sg.test.util.EntityUtils.updateStudent;
import static org.sg.test.util.EntityUtils.createAddress;
import static org.sg.test.util.EntityUtils.updateAddress;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.dao.AddressDao;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.AddressDaoImpl;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;

public class StudentDaoImplTest {
	
	public static Logger logger = LogManager.getLogger(StudentDaoImplTest.class);
	StudentDao studentDao = new StudentDaoImpl();
	AddressDao addressDao = new AddressDaoImpl();
	
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
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		
		//Then
		assertNotNull(studentEntity.getAddressEntity());
		assertEquals(studentEntity.getName(), "Sandro");
		assertEquals(studentEntity.getSurname(), "Gargano");
		assertEquals(studentEntity.getJobTitle(), "Waiter");
		assertEquals(studentEntity.getPaymentType(), "Confirmed");
		assertEquals(studentEntity.getSex(), Character.valueOf('M'));
		assertEquals(studentEntity.getAddressEntity().getId(), studentEntity.getId());
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
		studentEntity = studentDao.insert(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		
		//Then
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
		assertNull(studentEntityDb.getAddressEntity());
	}

	@Test
	public void test_insert_withAddress() {
		//Given
		StudentEntity studentEntity = createStudentWithAddress();
		
		//When
		studentEntity = studentDao.insert(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		
		//Then
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
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
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	}
	
	@Test
	public void test_add_address_for_existingStudent() {
		//Given
		final Integer studentId = 23;
		final AddressEntity addressEntity = createAddress();
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		studentEntity.setAddressEntity(addressEntity);
		addressEntity.setStudentEntity(studentEntity);
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		
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
		studentEntity = studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentId);
		
		//Then
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	}

	@Test
	public void test_update_address_for_existingStudent() {
		//Given
		final Integer studentId = 3;
		
		//When
		StudentEntity studentEntity = studentDao.get(studentId);
		AddressEntity addressEntity = updateAddress(studentEntity.getAddressEntity());
		studentEntity = studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		
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
