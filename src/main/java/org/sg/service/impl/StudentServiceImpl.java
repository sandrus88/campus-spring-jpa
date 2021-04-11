package org.sg.service.impl;

import java.util.List;

import org.sg.dao.AddressDao;
import org.sg.dao.StudentDao;
import org.sg.entities.AddressEntity;
import org.sg.entities.StudentEntity;
import org.sg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	final private StudentDao studentDao;
	final private AddressDao addressDao;
	
	@Autowired
	public StudentServiceImpl(StudentDao studentDao, AddressDao addressDao) {
		this.studentDao = studentDao;
		this.addressDao = addressDao;
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
	public AddressEntity insert(AddressEntity addressEntity) {
		return addressDao.insert(addressEntity);
	}

	@Override
	public AddressEntity get(AddressEntity addressEntity) {
		return addressDao.get(addressEntity);
	}

	@Override
	public AddressEntity update(AddressEntity addressEntity) {
		return addressDao.update(addressEntity);
	}

	public void delete(AddressEntity addressEntity) {
		addressDao.delete(addressEntity);
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		return studentDao.getAll();
	}

	@Override
	public List<AddressEntity> getAllAddresses() {
		return addressDao.getAll();
	}
}
