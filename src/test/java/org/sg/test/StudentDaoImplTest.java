package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
	public void test_insert() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName("Name1");
		studentEntity.setSurname("Surname1");
		studentEntity.setJobTitle("Job1");
		studentEntity.setPaymentType("Confirmed");
		studentEntity.setSex('M');
		logger.info("Studente da inserire: " + studentEntity);

		studentEntity = studentDao.insert(studentEntity);
		logger.info("Studente inserito: " + studentEntity);
		assertNotNull(studentEntity.getId());

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		logger.info("Studente nel db: " + studentEntityDb);
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
		assertNull(studentEntityDb.getAddressEntity());
	}

	@Test
	public void test_insert_withAddress() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName("Name_withAddress");
		studentEntity.setSurname("Surname_withAddress");
		studentEntity.setJobTitle("Job_withAddress");
		studentEntity.setPaymentType("Confirmed");
		studentEntity.setSex('M');
		
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet("Via del pentimento");
		addressEntity.setNr("30");
		addressEntity.setPostalCode(50122);
		addressEntity.setCity("Firenze");
		addressEntity.setProvinceCode("FI");
		addressEntity.setStudentEntity(studentEntity);
		
		studentEntity.setAddressEntity(addressEntity);
		logger.info("Studente da inserire: " + studentEntity);
		
		studentEntity = studentDao.insert(studentEntity);
		logger.info("Studente inserito con indirizzo: " + studentEntity);

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		logger.info("Studente nel db: " + studentEntityDb);
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
		assertEquals(studentEntityDb.getAddressEntity(), studentEntity.getAddressEntity());
	}

	@Test
	public void test_get_withoutAddress() {
		final Integer studentId = 21;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		logger.info(studentEntity);

		assertEquals(studentEntity.getName(), "Armela");
		assertEquals(studentEntity.getSurname(), "Xhaxho");
		assertEquals(studentEntity.getJobTitle(), "Shop Assistant");
		assertEquals(studentEntity.getPaymentType(), "To be confirmed");
		assertEquals(studentEntity.getSex(), Character.valueOf('F'));
	}

	@Test
	public void test_get_withAddress() {
		final Integer studentId = 1;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		AddressEntity addressEntity = studentEntity.getAddressEntity();
		assertNotNull(addressEntity);
		logger.info(studentEntity);

		assertEquals(studentEntity.getName(), "Sandro");
		assertEquals(studentEntity.getSurname(), "Gargano");
		assertEquals(studentEntity.getJobTitle(), "Waiter");
		assertEquals(studentEntity.getPaymentType(), "Confirmed");
		assertEquals(studentEntity.getSex(), Character.valueOf('M'));
		assertEquals(addressEntity.getId(), studentEntity.getId());
	}

	@Test
	public void test_get_notPresent() {
		final Integer studentId = -1;
		StudentEntity studentEntity = studentDao.get(studentId);
		logger.info(studentEntity);
		assertNull(studentEntity);
	}

	@Test
	public void test_update_withoutAddress() {
		final Integer studentId = 22;
		StudentEntity studentEntity = studentDao.get(studentId);
		logger.info("Prima dell'update " + studentEntity);
		assertNotNull(studentEntity);

		studentEntity.setName("NameUpdated7");
		studentEntity.setSurname("SurnameUpdated7");
		studentEntity.setJobTitle("JobUpdated7");
		studentEntity.setPaymentType("Not Confirmed");
		studentEntity.setSex('M');

		studentEntity = studentDao.update(studentEntity);
		logger.info("Dopo l'update" + studentEntity);
		assertNotNull(studentEntity);

		StudentEntity studentEntityDb = studentDao.get(studentId);
		logger.info("Studente nel db: " + studentEntityDb);
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	}
	
	@Test
	public void test_add_address_for_existingStudent() {
		final Integer studentId = 23;
		StudentEntity studentEntity = studentDao.get(studentId);
		logger.info("Studente senza indirizzo: " + studentEntity);
		assertNotNull(studentEntity);
		assertNull(studentEntity.getAddressEntity());
		
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet("Via del ponte di mezzo");
		addressEntity.setNr("42");
		addressEntity.setPostalCode(50127);
		addressEntity.setCity("Firenze");
		addressEntity.setProvinceCode("FI");
		addressEntity.setStudentEntity(studentEntity);
		
		studentEntity.setAddressEntity(addressEntity);
		logger.info("Indirizzo da inserire: " + addressEntity);
		
		studentDao.update(studentEntity);
		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		logger.info("Studente nel db con indirizzo aggiunto: " + studentEntityDb);
		assertNotNull(studentEntityDb);	
		assertNotNull(studentEntityDb.getAddressEntity());
		assertNotNull(studentEntityDb.getAddressEntity().getId());
		assertEquals(addressEntity.getId(), studentEntity.getId());
	}
	
	@Test
	public void test_update_student_withAddress_addressShouldNotChange() {
		final Integer studentId = 2;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		AddressEntity addressEntity = studentEntity.getAddressEntity();
		assertNotNull(addressEntity);
		logger.info("Prima dell'update " + studentEntity);

		studentEntity.setName("NameUpdated");
		studentEntity.setSurname("SurnameUpdated");
		studentEntity.setJobTitle("JobUpdated");
		studentEntity.setPaymentType("To be confirmed");
		studentEntity.setSex('F');

		studentEntity = studentDao.update(studentEntity);
		logger.info("Dopo l'update" + studentEntity);

		StudentEntity studentEntityDb = studentDao.get(studentId);
		logger.info("Studente nel db: " + studentEntityDb);
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getName(), studentEntity.getName());
		assertEquals(studentEntityDb.getSurname(), studentEntity.getSurname());
		assertEquals(studentEntityDb.getJobTitle(), studentEntity.getJobTitle());
		assertEquals(studentEntityDb.getPaymentType(), studentEntity.getPaymentType());
		assertEquals(studentEntityDb.getSex(), studentEntity.getSex());
	}

	@Test
	public void test_update_address_for_existingStudent() {
		final Integer studentId = 3;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		AddressEntity addressEntity = studentEntity.getAddressEntity();
		assertNotNull(addressEntity);
		logger.info("Indirizzo prima dell'update " + addressEntity);

		addressEntity.setStreet("UpdatedStreet");
		addressEntity.setNr("UpdatedNr");
		addressEntity.setPostalCode(0000);
		addressEntity.setCity("UpdatedCity");
		addressEntity.setProvinceCode("UC");

		studentEntity = studentDao.update(studentEntity);
		logger.info("Indirizzo dopo l'update" + addressEntity);

		StudentEntity studentEntityDb = studentDao.get(studentEntity.getId());
		logger.info("Studente nel db con l'indirizzo modificato: " + studentEntityDb);
		assertNotNull(studentEntityDb);
		assertEquals(studentEntityDb.getAddressEntity().getStreet(), studentEntity.getAddressEntity().getStreet());
		assertEquals(studentEntityDb.getAddressEntity().getNr(), studentEntity.getAddressEntity().getNr());
		assertEquals(studentEntityDb.getAddressEntity().getPostalCode(), studentEntity.getAddressEntity().getPostalCode());
		assertEquals(studentEntityDb.getAddressEntity().getCity(), studentEntity.getAddressEntity().getCity());
		assertEquals(studentEntityDb.getAddressEntity().getProvinceCode(), studentEntity.getAddressEntity().getProvinceCode());
	}

	@Test
	public void test_delete() {
		final Integer studentId = 24;

		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);

		boolean deleting = studentDao.delete(studentId);
		studentEntity = studentDao.get(studentId);
		logger.info("Dopo l'eliminazione " + studentEntity);
		assertTrue(deleting);
		assertNull(studentEntity);
	}

	@Test
	public void test_delete_notPresent() {
		final Integer studentId = -1;
		boolean deleting = studentDao.delete(studentId);
		assertFalse(deleting);
	}

	@Test
	public void test_delete_WithAddress() {
		final Integer studentId = 4;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getAddressEntity());
		logger.info("Prima dell'eliminazione " + studentEntity);
	
		boolean deleting = studentDao.delete(studentId);
		assertTrue(deleting);

		studentEntity = studentDao.get(studentId);
		logger.info("Dopo l'eliminazione " + studentEntity);
		assertNull(studentEntity);
	}
	
	@Test
	public void test_delete_address() {
		final Integer studentId = 5;
		StudentEntity studentEntity = studentDao.get(studentId);
		assertNotNull(studentEntity);
		assertNotNull(studentEntity.getAddressEntity());
		logger.info("Indirizzo prima dell'eliminazione " + studentEntity.getAddressEntity());
		
//		addressDao.delete(addressEntity);
		studentEntity.setAddressEntity(new AddressEntity());
		studentDao.update(studentEntity);
		
		StudentEntity db = studentDao.get(studentEntity.getId());
		assertNotNull(db);
		assertNull(db.getAddressEntity());
		logger.info("Indirizzo dopo l'eliminazione " + db.getAddressEntity());
	}
}
