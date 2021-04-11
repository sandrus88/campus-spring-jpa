package org.sg.service.impl;

import java.util.List;

import org.sg.dao.CourseDao;
import org.sg.dao.ExamDao;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.dao.impl.ExamDaoImpl;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.ExamEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.ExamService;

public class ExamServiceImpl implements ExamService{
	
	private StudentDao studentDao;
	private CourseDao courseDao;
	private ExamDao examDao;
		
		public ExamServiceImpl() {
			studentDao = new StudentDaoImpl();
			courseDao = new CourseDaoImpl();
			examDao = new ExamDaoImpl();
		}

	public StudentEntity insert(StudentEntity studentEntity) {
		return studentDao.insert(studentEntity);
	}

	public StudentEntity getStudent(Integer id) {
		return studentDao.get(id);
	}

	public StudentEntity update(StudentEntity studentEntity) {
		return studentDao.update(studentEntity);
	}

	public boolean deleteStudent(Integer id) {
		return studentDao.delete(id);
	}

	@Override
	public CourseEntity insert(CourseEntity courseEntity) {
		return courseDao.insert(courseEntity);
	}

	@Override
	public CourseEntity get(Integer id) {
		return courseDao.get(id);
	}

	@Override
	public CourseEntity update(CourseEntity courseEntity) {
		return courseDao.update(courseEntity);
	}

	@Override
	public boolean delete(Integer id) {
		return courseDao.delete(id);
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		return studentDao.getAll();
	}

	@Override
	public List<CourseEntity> getAllCourses() {
		return courseDao.getAll();
	}

	@Override
	public List<ExamEntity> getAllExams() {
		return examDao.getAll();
	}
}
