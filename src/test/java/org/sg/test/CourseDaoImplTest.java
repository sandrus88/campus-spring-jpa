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

import org.junit.Test;
import org.sg.entities.CourseEntity;
import org.sg.entities.TopicEntity;
import org.sg.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseDaoImplTest {
	
//	ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
//	
//	CourseService courseService = (CourseService)appContext.getBean("courseServiceImpl");
	@Autowired
	private CourseService courseService;

	@Test
	public void test_getCourse_withoutTopics() {
		// Given
		final Integer courseId = 205;

		// When
		CourseEntity courseEntity = courseService.get(courseId);

		// Then
		assertNotNull(courseEntity);
		assertEquals(courseEntity.getName(), "Javascript");
		assertEquals(courseEntity.getDescription(), "Concetti base di Javascript");
	}

	@Test
	public void test_getCourse_withTopics() {
		// Given
		final Integer courseId = 201;

		// When
		CourseEntity courseEntity = courseService.get(courseId);

		// Then
		assertNotNull(courseEntity.getTopics());
		assertEquals(courseEntity.getTopics().get(0).getId(), Integer.valueOf(1));
		assertEquals(courseEntity.getTopics().get(1).getId(), Integer.valueOf(2));
		assertEquals(courseEntity.getTopics().get(2).getId(), Integer.valueOf(3));
		assertEquals(courseEntity.getTopics().get(3).getId(), Integer.valueOf(4));
		assertEquals(courseEntity.getTopics().get(4).getId(), Integer.valueOf(6));
	}

	@Test
	public void test_getCourse_notPresent() {
		// Given
		final Integer courseId = -1;

		// When
		CourseEntity courseEntity = courseService.get(courseId);

		// Then
		assertNull(courseEntity);
	}

	@Test
	public void test_insertCourse_withoutTopics() {
		// Given
		CourseEntity courseEntity = createCourse();

		// When
		courseService.insert(courseEntity);
		CourseEntity courseEntityDb = courseService.get(courseEntity.getId());

		// Then
		assertEquals(courseEntityDb, courseEntity);
		assertTrue(courseEntityDb.getTopics().isEmpty());
	}

	@Test
	public void test_insertCourse_withTopics() {
		// Given
		CourseEntity courseEntity = createCourse();
		TopicEntity topicEntity = createTopic(courseEntity);

		// When
		courseService.insert(courseEntity);
		CourseEntity courseEntityDb = courseService.get(courseEntity.getId());

		// Then
		assertNotNull(courseEntity.getTopicById(topicEntity.getId()));
		assertEquals(courseEntityDb, courseEntity);
		assertEquals(courseEntityDb.getTopics(), courseEntity.getTopics());
	}
	
	@Test
	public void test_addTopic_forCourse() {
		//Given
		final Integer courseId = 208;
		
		//When
		CourseEntity courseEntity = courseService.get(courseId);
		TopicEntity topicEntity = createTopic(courseEntity);
		courseEntity.addTopic(topicEntity);
		courseService.update(courseEntity);
		CourseEntity courseEntityDb = courseService.get(courseId);
		
		//Then
		assertNotNull(courseEntityDb.getTopicById(topicEntity.getId()));
		assertEquals(courseEntityDb.getTopicById(topicEntity.getId()), topicEntity);
	}
	
	@Test
	public void test_deleteTopic_forCourse() {
		// Given
		final Integer courseId = 206;
		final Integer topicId = 15;
		
		// When
		CourseEntity courseEntity = courseService.get(courseId);
		courseEntity.removeTopicById(topicId);
		courseService.update(courseEntity);
		CourseEntity courseEntityDb = courseService.get(courseId);
		
		// Then
		assertNull(courseEntityDb.getTopicById(topicId));
	}
	
	@Test
	public void test_updateTopic_forCourse() {
		//Given
		final Integer courseId = 203;
		final Integer topicId = 10;
		
		//When
		CourseEntity courseEntity = courseService.get(courseId);
		TopicEntity topicEntity	= courseEntity.getTopicById(topicId);
		updateTopic(topicEntity);
		courseService.update(courseEntity);
		CourseEntity courseEntityDb = courseService.get(courseId);
		
		//Then
		assertEquals(courseEntityDb.getTopicById(topicId), topicEntity);
	}
	
	@Test
	public void test_updateCourse_withoutTopics() {
		//Given
		final Integer courseId = 207;
		
		//When
		CourseEntity courseEntity = courseService.get(courseId);
		courseEntity = updateCourse(courseEntity);
		courseService.update(courseEntity);
		CourseEntity courseEntityDb = courseService.get(courseId);
		
		//Then
		assertEquals(courseEntityDb, courseEntity);
		assertEquals(0, courseEntity.getTopics().size());
		assertEquals(0, courseEntityDb.getTopics().size());
	}
	
	@Test
	public void test_updateCourse_withTopics() {
		//Given
		final Integer courseId = 202;
		
		//When
		CourseEntity courseEntity = courseService.get(courseId);
		courseEntity = updateCourse(courseEntity);
		courseService.update(courseEntity);
		CourseEntity courseEntityDb = courseService.get(courseId);
		
		//Then
		assertEquals(2, courseEntity.getTopics().size());
		assertEquals(courseEntityDb, courseEntity);
		assertEquals(2, courseEntityDb.getTopics().size());
		assertEquals(courseEntityDb.getTopics().get(0), courseEntityDb.getTopics().get(0));
		assertEquals(courseEntityDb.getTopics().get(1), courseEntityDb.getTopics().get(1));
	}
	
	@Test
	public void test_deleteCourse() {
		// Given
		final Integer courseId = 209;

		// When
		boolean deleting = courseService.delete(courseId);
		CourseEntity courseEntity = courseService.get(courseId);

		// Then
		assertTrue(deleting);
		assertNull(courseEntity);
	}

	@Test
	public void test_deleteCourse_notPresent() {
		// Given
		final Integer courseId = -1;

		// When
		boolean deleting = courseService.delete(courseId);

		// Then
		assertFalse(deleting);
	}

	@Test
	public void test_deleteCourse_WithTopics() {
		// Given
		final Integer courseId = 204;

		// When
		boolean deleting = courseService.delete(courseId);
		CourseEntity courseEntity = courseService.get(courseId);

		// Then
		assertTrue(deleting);
		assertNull(courseEntity);
	}
	
	@Test
	public void test_course_integrationTest_CRUD() {
		// 1. insert a new course
		CourseEntity courseEntity = createCourse();
		courseService.insert(courseEntity);
		assertNotNull(courseEntity.getId());
		
		// 2. get the course from DB
		CourseEntity courseEntityDb = courseService.get(courseEntity.getId());
		assertNotNull(courseEntityDb);
		assertEquals(courseEntityDb, courseEntity);
		
		// 3. Update the course in DB, and Get to check if updated correctly
		updateCourse(courseEntity);
		courseService.update(courseEntity);
		courseEntityDb = courseService.get(courseEntity.getId());
		assertEquals(courseEntityDb, courseEntity);
		
		// 4. Delete the course from DB, and Get to check if deleted correctly
		boolean isRemoved = courseService.delete(courseEntity.getId());
		assertTrue(isRemoved);
		courseEntityDb = courseService.get(courseEntity.getId());
		assertNull(courseEntityDb);
	}
	
	@Test
	public void test_topic_integrationTest_CRUD() {
		// 1. insert a new course
		CourseEntity courseEntity = createCourse();
		courseService.insert(courseEntity);
		assertNotNull(courseEntity.getId());
		
		// 2. insert a new topic
		TopicEntity topicEntity = createTopic(courseEntity);
		courseService.update(courseEntity);
		assertNotNull(topicEntity.getId());
		
		// 3. get the topic from DB
		TopicEntity topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertNotNull(topicEntityDb);
		assertEquals(topicEntityDb, topicEntity);
		
		// 4. Update the topic in DB, and Get to check if updated correctly
		updateTopic(topicEntity);
		courseService.update(courseEntity);
		topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertEquals(topicEntityDb, topicEntity);
		
		// 5. Delete the topic from DB, and Get to check if deleted correctly
		courseEntity.removeTopicById(topicEntity.getId());
		courseService.update(courseEntity);
		topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertNull(topicEntityDb);
	}
}
