package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createAddress;
import static org.sg.test.util.EntityUtils.createStudent;
import static org.sg.test.util.EntityUtils.createStudentWithAddress;
import static org.sg.test.util.EntityUtils.updateAddress;
import static org.sg.test.util.EntityUtils.updateStudent;

import org.junit.Test;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.StudentService;
import org.sg.service.impl.StudentServiceImpl;

public class StudentDaoImpl_IntegrationTest {
	private StudentService crud = new StudentServiceImpl();

	@Test
	public void test_CRUD_student() {
		// 1. insert a new student in DB
		StudentEntity studentEntity = createStudent();
		studentEntity = crud.insert(studentEntity);
		assertNotNull(studentEntity.getId());

		// 2. get the student from DB
		StudentEntity studentEntityDb = crud.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb, studentEntity);

		// 3. Update the student in DB, and Get to check if updated correctly
		studentEntity = updateStudent(studentEntity);
		crud.update(studentEntity);
		studentEntityDb = crud.getStudent(studentEntity.getId());
		assertEquals(studentEntityDb, studentEntity);

		// 4. Delete the student from DB, and Get to check if deleted correctly
		boolean isRemoved = crud.deleteStudent(studentEntity.getId());
		assertTrue(isRemoved);
		studentEntityDb = crud.getStudent(studentEntity.getId());
		assertNull(studentEntityDb);
	}

	@Test
	public void test_CRUD_address() {
		// 1. insert a new student
		StudentEntity studentEntity = createStudent();
		studentEntity = crud.insert(studentEntity);
		assertNotNull(studentEntity.getId());

		// 2. insert an address for the student
		AddressEntity addressEntity = createAddress();
		assertNotNull(addressEntity);
		addressEntity.setStudentEntity(studentEntity);
		studentEntity.setAddressEntity(addressEntity);
		crud.update(studentEntity);

		// 3. Get and check if the student and the address has correctly been fetched
		StudentEntity studentEntityDb = crud.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertNotNull(studentEntityDb.getAddressEntity());
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());

		// 4. Update the address, and Get to check if is updated correctly
		updateAddress(studentEntity.getAddressEntity());
		crud.update(studentEntity);
		studentEntityDb = crud.getStudent(studentEntity.getId());
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());

		// 5. Delete the address of the student, and Get to check if is deleted
		// correctly
		studentEntity.getAddressEntity().setStudentEntity(null);
		studentEntity.setAddressEntity(null);
		crud.update(studentEntity);
		studentEntityDb = crud.getStudent(studentEntity.getId());
		assertNull(studentEntityDb.getAddressEntity());
	}

	@Test
	public void test_CRUD_studentWithAddress() {
		// 1. insert a new student with address
		StudentEntity studentEntity = createStudentWithAddress();
		studentEntity = crud.insert(studentEntity);
		assertNotNull(studentEntity.getId());
		assertNotNull(studentEntity.getAddressEntity());

		// 2. Get and check if the student and the address has correctly been fetched
		StudentEntity studentEntityDb = crud.getStudent(studentEntity.getId());
		assertNotNull(studentEntityDb);
		assertNotNull(studentEntityDb.getAddressEntity());
		assertEquals(studentEntityDb, studentEntity);
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());

		// 3. Delete the student, and Get to check if is deleted correctly
		boolean isRemoved = crud.deleteStudent(studentEntity.getId());
		assertTrue(isRemoved);
		studentEntityDb = crud.getStudent(studentEntity.getId());
		assertNull(studentEntityDb);
	}
}