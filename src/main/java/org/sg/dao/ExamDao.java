package org.sg.dao;

import org.sg.entities.ExamEntity;

public interface ExamDao {
	ExamEntity insert(ExamEntity examEntity);
	ExamEntity get(Integer id);
	ExamEntity update(ExamEntity examEntity);
	boolean delete(Integer id);
}
