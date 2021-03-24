package org.sg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sg.test.util.EntityUtils.createCourse;
import static org.sg.test.util.EntityUtils.createTopic;
import static org.sg.test.util.EntityUtils.updateCourse;
import static org.sg.test.util.EntityUtils.updateTopic;

import org.junit.Test;
import org.sg.dao.impl.CourseDaoImpl;
import org.sg.dao.impl.TopicDaoImpl;
import org.sg.entities.CourseEntity;
import org.sg.entities.TopicEntity;

public class CourseDaoImpl_IntegrationTest {
	public CourseDaoImpl crud = new CourseDaoImpl();
	public TopicDaoImpl crudTopic = new TopicDaoImpl();
	
	@Test
	public void test_CRUD_course() {
			// 1. insert a new course
			CourseEntity courseEntity = createCourse();
			courseEntity = crud.insert(courseEntity);
			assertNotNull(courseEntity.getId());
			
			// 2. get the course from DB
			CourseEntity courseEntityDb = crud.get(courseEntity.getId());
			assertNotNull(courseEntityDb);
			assertEquals(courseEntityDb, courseEntity);

			// 3. Update the course in DB, and Get to check if updated correctly
			courseEntity = updateCourse(courseEntity);
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
			// 1. insert a new topic
			TopicEntity topicEntity = createTopic();
			topicEntity = crudTopic.insert(topicEntity);
			assertNotNull(topicEntity.getId());
			
			// 2. get the topic from DB
			TopicEntity topicEntityDb = crudTopic.get(topicEntity.getId());
			assertNotNull(topicEntityDb);
			assertEquals(topicEntityDb, topicEntity);

			// 3. Update the topic in DB, and Get to check if updated correctly
			topicEntity = updateTopic(topicEntity);
			crudTopic.update(topicEntity);
			topicEntityDb = crudTopic.get(topicEntity.getId());
			assertEquals(topicEntityDb, topicEntity);

			// 4. Delete the topic from DB, and Get to check if deleted correctly
			boolean isRemoved = crudTopic.delete(topicEntity.getId());
			assertTrue(isRemoved);
			topicEntityDb = crudTopic.get(topicEntity.getId());
			assertNull(topicEntityDb);
	}
	
	@Test
	public void test_CRUD_courseWithTopics() {
		// 1. insert a new course with topics
		CourseEntity courseEntity = createCourse();
		courseEntity = crud.insert(courseEntity);
		TopicEntity topicEntity = createTopic();
		topicEntity = crudTopic.insert(topicEntity);
		courseEntity.addTopic(topicEntity);
		topicEntity.setCourseEntity(courseEntity);
		crud.update(courseEntity);
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
