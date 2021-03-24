package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createCourse;
import static org.sg.test.util.EntityUtils.createTopic;
import static org.sg.test.util.EntityUtils.updateCourse;
import static org.sg.test.util.EntityUtils.updateTopic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sg.dao.CourseDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.TopicEntity;

public class CourseDaoImplTest {

	public static Logger logger = LogManager.getLogger(StudentDaoImplTest.class);
	CourseDao courseDao = new CourseDaoImpl();

	@Test
	public void test_get_withoutTopics() {
		// Given
		final Integer courseId = 205;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntity);
		assertEquals(courseEntity.getName(), "Javascript");
		assertEquals(courseEntity.getDescription(), "Concetti base di Javascript");
	}

	@Test
	public void test_get_withTopics() {
		// Given
		final Integer courseId = 201;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntity.getTopics());
		assertEquals(courseEntity.getTopics().get(0).getId(), Integer.valueOf(301));
		assertEquals(courseEntity.getTopics().get(1).getId(), Integer.valueOf(302));
		assertEquals(courseEntity.getTopics().get(2).getId(), Integer.valueOf(303));
		assertEquals(courseEntity.getTopics().get(3).getId(), Integer.valueOf(304));
		assertEquals(courseEntity.getTopics().get(4).getId(), Integer.valueOf(306));
	}

	@Test
	public void test_get_notPresent() {
		// Given
		final Integer courseId = -1;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertNull(courseEntity);
	}

	@Test
	public void test_insert() {
		// Given
		CourseEntity courseEntity = createCourse();

		// When
		courseEntity = courseDao.insert(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());

		// Then
		assertEquals(courseEntityDb.getName(), courseEntity.getName());
		assertEquals(courseEntityDb.getDescription(), courseEntity.getDescription());
		assertNull(courseEntityDb.getTopics());
	}

	@Test
	public void test_insert_withTopics() {
		// Given
		CourseEntity courseEntity = createCourse();
		TopicEntity topicEntity = createTopic();

		// When
		courseEntity.getTopics().add(topicEntity);
		topicEntity.setCourseEntity(courseEntity);
		courseDao.insert(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());

		// Then
		assertEquals(courseEntityDb.getName(), courseEntity.getName());
		assertEquals(courseEntityDb.getDescription(), courseEntity.getDescription());
		assertEquals(courseEntityDb.getTopics(), courseEntity.getTopics());
	}
	
	@Test
	public void test_update_withoutTopics() {
		//Given
		final Integer courseId = 207;
		
		//When
		CourseEntity courseEntity = updateCourse(courseDao.get(courseId));
		courseDao.insert(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());
		
		//Then
		assertEquals(courseEntityDb.getName(), courseEntity.getName());
		assertEquals(courseEntityDb.getDescription(), courseEntity.getDescription());
	}
	
	@Test
	public void test_add_topic_for_existingCourse() {
		//Given
		final Integer courseId = 208;
		final TopicEntity topicEntity = createTopic();
		
		//When
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.addTopic(topicEntity);
		topicEntity.setCourseEntity(courseEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());
		
		//Then
		assertNotNull(courseEntityDb.getTopicById(topicEntity.getId()));
		assertEquals(courseEntityDb.getTopicById(topicEntity.getId()), topicEntity);
	}
	
	@Test
	public void test_update_student_withTopic_topicShouldNotChange() {
		//Given
		final Integer courseId = 202;
		
		//When
		CourseEntity courseEntity = updateCourse(courseDao.get(courseId));
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());
		
		//Then
		assertEquals(courseEntityDb.getName(), courseEntity.getName());
		assertEquals(courseEntityDb.getDescription(), courseEntity.getDescription());
	}

	@Test
	public void test_update_topic_for_existingCourse() {
		//Given
		final Integer courseId = 203;
		final Integer topicId = 309;
		
		//When
		CourseEntity courseEntity = courseDao.get(courseId);
		TopicEntity topicEntity = updateTopic(courseEntity.getTopicById(topicId));
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());
		
		//Then
		assertEquals(courseEntityDb.getTopicById(topicId), topicEntity);
	}

	@Test
	public void test_delete() {
		// Given
		final Integer courseId = 209;

		// When
		boolean deleting = courseDao.delete(courseId);
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertTrue(deleting);
		assertNull(courseEntity);
	}

	@Test
	public void test_delete_notPresent() {
		// Given
		final Integer courseId = -1;

		// When
		boolean deleting = courseDao.delete(courseId);

		// Then
		assertFalse(deleting);
	}

	@Test
	public void test_delete_WithTopics() {
		// Given
		final Integer courseId = 204;

		// When
		boolean deleting = courseDao.delete(courseId);
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertTrue(deleting);
		assertNull(courseEntity);
	}

	@Test
	public void test_delete_topic() {
		// Given
		final Integer courseId = 206;
		final Integer topicId = 315;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeTopicById(topicId);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());

		// Then
		assertNull(courseEntityDb.getTopicById(topicId));
	}
}
