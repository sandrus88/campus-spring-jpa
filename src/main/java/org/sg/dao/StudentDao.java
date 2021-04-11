package org.sg.dao;

import java.util.List;

import org.sg.entities.StudentEntity;

public interface StudentDao {
	StudentEntity insert(StudentEntity studentEntity);
	StudentEntity get(Integer id);
	StudentEntity update(StudentEntity studentEntity);
	List<StudentEntity> getAll();
	boolean delete(Integer id);
}
