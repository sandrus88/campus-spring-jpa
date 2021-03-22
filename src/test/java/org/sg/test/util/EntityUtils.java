package org.sg.test.util;

import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;

public class EntityUtils {

	public static StudentEntity createStudent() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName("newName");
		studentEntity.setSurname("newSurname");
		studentEntity.setJobTitle("newJob");
		studentEntity.setPaymentType("Confirmed");
		studentEntity.setSex('M');
		return studentEntity;
	}

	public static StudentEntity updateStudent() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName("updatedName");
		studentEntity.setSurname("updatedSurname");
		studentEntity.setJobTitle("updatedJJobTitle");
		studentEntity.setPaymentType("Confirmed");
		studentEntity.setSex('M');
		return studentEntity;
	}
	
	public static StudentEntity createStudentWithAddress() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setName("newNameWithAddress");
		studentEntity.setSurname("newSurnameWithAddress");
		studentEntity.setJobTitle("newJobWithAddress");
		studentEntity.setPaymentType("Confirmed");
		studentEntity.setSex('M');
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet("newStreetForStudent");
		addressEntity.setNr("newNumberForStudent");
		addressEntity.setPostalCode(11111);
		addressEntity.setCity("newCityForStudent");
		addressEntity.setProvinceCode("AS");
		addressEntity.setStudentEntity(studentEntity);
		studentEntity.setAddressEntity(addressEntity);
		return studentEntity;
	}

	public static AddressEntity createAddress() {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet("newStreet");
		addressEntity.setNr("newNumber");
		addressEntity.setPostalCode(00000);
		addressEntity.setCity("newCity");
		addressEntity.setProvinceCode("NA");
		return addressEntity;
	}
	
	public static AddressEntity updateAddress() {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet("updatedStreet");
		addressEntity.setNr("updatedNumber");
		addressEntity.setPostalCode(99999);
		addressEntity.setCity("updatedCity");
		addressEntity.setProvinceCode("UA");
		return addressEntity;
	}
}
