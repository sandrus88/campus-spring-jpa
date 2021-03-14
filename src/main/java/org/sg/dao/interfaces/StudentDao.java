package org.sg.dao.interfaces;

import org.sg.entities.StudentEntity;

public interface StudentDao {
	StudentEntity createStudent(StudentEntity studentEntity);
	StudentEntity get(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	boolean delete(Integer id);
}
