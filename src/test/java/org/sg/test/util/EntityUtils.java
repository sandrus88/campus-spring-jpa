package org.sg.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sg.entities.AddressEntity;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;
import org.sg.entities.TopicEntity;

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

	public static StudentEntity updateStudent(StudentEntity studentEntity) {
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
	
	public static CourseEntity createCourse() {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setName("newCourse");
		courseEntity.setDescription("descriptionOfNewCourse");
		return courseEntity;
	}
	
	public static CourseEntity updateCourse(CourseEntity courseEntity) {
		courseEntity.setName("updatedCourse");
		courseEntity.setDescription("descriptionOfUpdatedCourse");
		return courseEntity;
	}
	
	public static TopicEntity createTopic(CourseEntity courseEntity) {
		TopicEntity topicEntity = new TopicEntity();
		topicEntity.setName("newTopic");
		topicEntity.setDescription("descriptionOfNewTopic");
		topicEntity.setCourseEntity(courseEntity);
		return topicEntity;
	}
	
	public static TopicEntity updateTopic(TopicEntity topicEntity) {
		topicEntity.setName("updatedTopic");
		topicEntity.setDescription("descriptionOfUpdatedTopic");
		return topicEntity;
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
	
	public static AddressEntity updateAddress(AddressEntity addressEntity) {
		addressEntity.setStreet("updatedStreet");
		addressEntity.setNr("updatedNumber");
		addressEntity.setPostalCode(99999);
		addressEntity.setCity("updatedCity");
		addressEntity.setProvinceCode("UA");
		return addressEntity;
	}
	
	public static ExamEntity createExam(StudentEntity studentEntity, CourseEntity courseEntity) throws ParseException{
		ExamEntity examEntity = new ExamEntity();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("01/01/2021");
		
		examEntity.setExamDate(date);
		examEntity.setMark(18);
		examEntity.setCourseEntity(courseEntity);
		examEntity.setStudentEntity(studentEntity);
		return examEntity;
	}
	
	public static ExamEntity updateExam(ExamEntity examEntity) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("31/12/2021");
		examEntity.setExamDate(date);
		examEntity.setMark(30);
		return examEntity;
	}
}
