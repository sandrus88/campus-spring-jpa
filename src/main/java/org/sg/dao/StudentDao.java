package org.sg.dao;

import org.sg.entities.StudentEntity;

public interface StudentDao {
	StudentEntity createStudent(StudentEntity studentEntity);
	StudentEntity get(int id);
	StudentEntity update(StudentEntity studentEntity);
	boolean delete(int id);
}
