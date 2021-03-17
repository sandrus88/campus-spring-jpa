package org.sg.dao;

import org.sg.entities.StudentEntity;

public interface StudentDao {
	StudentEntity insert(StudentEntity studentEntity);
	StudentEntity get(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	boolean delete(Integer id);
}