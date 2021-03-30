package org.sg.service.impl;

import org.sg.dao.CourseDao;
import org.sg.dao.StudentDao;
import org.sg.entities.CourseEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {
	
	private StudentDao studentDao;
	private CourseDao courseDao;
	
	@Autowired
	public ExamServiceImpl(StudentDao studentDao, CourseDao courseDao) {
		this.studentDao = studentDao;
		this.courseDao = courseDao;
	}

	public StudentEntity insertStudent(StudentEntity studentEntity) {
		return studentDao.insert(studentEntity);
	}

	public StudentEntity getStudent(Integer id) {
		return studentDao.get(id);
	}

	public StudentEntity updateStudent(StudentEntity studentEntity) {
		return studentDao.update(studentEntity);
	}

	public boolean deleteStudent(Integer id) {
		return studentDao.delete(id);
	}

	@Override
	public CourseEntity insertCourse(CourseEntity courseEntity) {
		return courseDao.insert(courseEntity);
	}

	@Override
	public CourseEntity getCourse(Integer id) {
		return courseDao.get(id);
	}

	@Override
	public CourseEntity updateCourse(CourseEntity courseEntity) {
		return courseDao.update(courseEntity);
	}

	@Override
	public boolean deleteCourse(Integer id) {
		return courseDao.delete(id);
	}
}
