package org.sg.service;

import java.util.List;

import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;

public interface StudentService {
	StudentEntity insert(StudentEntity studentEntity);
	StudentEntity getStudent(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	List<StudentEntity> getAllStudents();
	boolean deleteStudent(Integer id);
	AddressEntity insert(AddressEntity addressEntity);
	AddressEntity get(AddressEntity addressEntity);
	AddressEntity update(AddressEntity addressEntity);
	List<AddressEntity> getAllAddresses();
	void delete(AddressEntity addressEntity);
	
}
