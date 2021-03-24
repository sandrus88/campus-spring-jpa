package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.dao.AddressDao;
import org.sg.dao.CourseDao;
import org.sg.dao.StudentDao;
import org.sg.dao.impl.AddressDaoImpl;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.dao.impl.StudentDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.StudentEntity;

public class CourseDaoImplTest {
	
	public static Logger logger = LogManager.getLogger(StudentDaoImplTest.class);
	CourseDao coursetDao = new CourseDaoImpl();
	
	@Test
	public void test_get_withoutTopics() {
		//Given
		final Integer courseId = 204;
		
		//When
		CourseEntity courseEntity = coursetDao.get(courseId);
		
		//Then
		assertNotNull(courseEntity);
		assertEquals(courseEntity.getName(), "Computer Network");
		assertEquals(courseEntity.getDescription(), null);
	}

	@Test
	public void test_get_withTopics() {
		//Given
		final Integer courseId = 200;
		
		//When
		CourseEntity courseEntity = coursetDao.get(courseId);
		
		//Then
		logger.info(courseEntity.getTopics());
		assertNotNull(courseEntity.getTopics());
		assertEquals(courseEntity.getTopics().get(0).getId(), Integer.valueOf(301));
		assertEquals(courseEntity.getTopics().get(1).getId(), Integer.valueOf(302));
		assertEquals(courseEntity.getTopics().get(2).getId(), Integer.valueOf(303));
		assertEquals(courseEntity.getTopics().get(3).getId(), Integer.valueOf(304));
		assertEquals(courseEntity.getTopics().get(4).getId(), Integer.valueOf(305));
		assertEquals(courseEntity.getTopics().get(5).getId(), Integer.valueOf(306));
		assertEquals(courseEntity.getTopics().get(6).getId(), Integer.valueOf(307));
	}

	@Test
	public void test_get_notPresent() {
		//Given
		final Integer courseId = -1;
		
		//When
		CourseEntity courseEntity = coursetDao.get(courseId);
		
		//Then
		assertNull(courseEntity);
	}
}
