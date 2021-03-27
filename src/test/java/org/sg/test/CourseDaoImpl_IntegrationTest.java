package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createCourse;
import static org.sg.test.util.EntityUtils.createTopic;
import static org.sg.test.util.EntityUtils.updateCourse;
import static org.sg.test.util.EntityUtils.updateTopic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sg.dao.CourseDao;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.TopicEntity;

public class CourseDaoImpl_IntegrationTest {
	private CourseDao crud = new CourseDaoImpl();

	@Test
	public void test_CRUD_course() {
		// 1. insert a new course
		CourseEntity courseEntity = createCourse();
		crud.insert(courseEntity);
		assertNotNull(courseEntity.getId());

		// 2. get the course from DB
		CourseEntity courseEntityDb = crud.get(courseEntity.getId());
		assertNotNull(courseEntityDb);
		assertEquals(courseEntityDb, courseEntity);

		// 3. Update the course in DB, and Get to check if updated correctly
		updateCourse(courseEntity);
		crud.update(courseEntity);
		courseEntityDb = crud.get(courseEntity.getId());
		assertEquals(courseEntityDb, courseEntity);

		// 4. Delete the course from DB, and Get to check if deleted correctly
		boolean isRemoved = crud.delete(courseEntity.getId());
		assertTrue(isRemoved);
		courseEntityDb = crud.get(courseEntity.getId());
		assertNull(courseEntityDb);
	}

	@Test
	public void test_CRUD_topic() {
		// 1. insert a new course
		CourseEntity courseEntity = createCourse();
		crud.insert(courseEntity);
		assertNotNull(courseEntity.getId());
		
		// 2. insert a new topic
		TopicEntity topicEntity = createTopic(courseEntity);
		crud.update(courseEntity);
		assertNotNull(topicEntity.getId());
		

		// 3. get the topic from DB
		TopicEntity topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertNotNull(topicEntityDb);
		assertEquals(topicEntityDb, topicEntity);

		// 4. Update the topic in DB, and Get to check if updated correctly
		updateTopic(topicEntity);
		crud.update(courseEntity);
		topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertEquals(topicEntityDb, topicEntity);

		// 5. Delete the topic from DB, and Get to check if deleted correctly
		courseEntity.removeTopicById(topicEntity.getId());
		crud.update(courseEntity);
		topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertNull(topicEntityDb);
	}

	@Test
	public void test_CRUD_courseWithTopics() {
		// 1. insert a new course with topics
		CourseEntity courseEntity = createCourse();
		TopicEntity topicEntity = createTopic(courseEntity);
		crud.insert(courseEntity);
		assertNotNull(courseEntity.getId());
		assertNotNull(topicEntity.getId());
		assertNotNull(courseEntity.getTopics());

		// 2. Get and check if the course and the topics have correctly been fetched
		CourseEntity courseEntityDb = crud.get(courseEntity.getId());
		assertNotNull(courseEntityDb);
		assertNotNull(courseEntityDb.getTopics());
		assertEquals(courseEntityDb, courseEntity);
		assertEquals(courseEntityDb.getTopics(), courseEntity.getTopics());

		// 3. Delete the course, and Get to check if is deleted correctly
		boolean isRemoved = crud.delete(courseEntity.getId());
		assertTrue(isRemoved);
		courseEntityDb = crud.get(courseEntity.getId());
		assertNull(courseEntityDb);
	}
}
