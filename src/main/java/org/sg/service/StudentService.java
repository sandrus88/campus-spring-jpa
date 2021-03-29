package org.sg.service;

import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;

public interface StudentService {
	StudentEntity insert(StudentEntity studentEntity);
	StudentEntity getStudent(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	boolean deleteStudent(Integer id);
	AddressEntity insert(AddressEntity addressEntity);
	AddressEntity get(AddressEntity addressEntity);
	AddressEntity update(AddressEntity addressEntity);
	void delete(AddressEntity addressEntity);
	
}
