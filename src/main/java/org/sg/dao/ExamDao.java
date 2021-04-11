package org.sg.dao;

import java.util.List;

import org.sg.entities.ExamEntity;

public interface ExamDao {
	ExamEntity insert(ExamEntity examEntity);
	ExamEntity get(Integer id);
	ExamEntity update(ExamEntity examEntity);
	List<ExamEntity> getAll();
	boolean delete(Integer id);
}
