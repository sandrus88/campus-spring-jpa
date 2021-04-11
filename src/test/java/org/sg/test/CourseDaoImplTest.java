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

import java.util.List;

import org.junit.Test;
import org.sg.entities.CourseEntity;
import org.sg.entities.TopicEntity;
import org.sg.service.CourseService;
import org.sg.service.impl.CourseServiceImpl;

public class CourseDaoImplTest {

	private CourseService courseDao = new CourseServiceImpl();

	@Test
	public void test_getCourse_withoutTopics() {
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
	public void test_getCourse_withTopics() {
		// Given
		final Integer courseId = 201;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntity.getTopics());
		assertEquals(courseEntity.getTopics().get(0).getId(), Integer.valueOf(1));
		assertEquals(courseEntity.getTopics().get(1).getId(), Integer.valueOf(2));
		assertEquals(courseEntity.getTopics().get(2).getId(), Integer.valueOf(3));
		assertEquals(courseEntity.getTopics().get(3).getId(), Integer.valueOf(4));
		assertEquals(courseEntity.getTopics().get(4).getId(), Integer.valueOf(6));
	}

	@Test
	public void test_getAllCourses() {
		// Given
		final Integer[] topicsId = { 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213 };

		// When
		List<CourseEntity> courses = courseDao.getAllCourses();
		System.out.println("List of courses " + courses);

		// Then
		assertNotNull(courses);
		assertEquals(courses.size(), 13);
		assertEquals(courses.get(0).getId(), topicsId[0]);
		assertEquals(courses.get(1).getId(), topicsId[1]);
		assertEquals(courses.get(2).getId(), topicsId[2]);
		assertEquals(courses.get(3).getId(), topicsId[3]);
		assertEquals(courses.get(4).getId(), topicsId[4]);
		assertEquals(courses.get(5).getId(), topicsId[5]);
		assertEquals(courses.get(6).getId(), topicsId[6]);
		assertEquals(courses.get(7).getId(), topicsId[7]);
		assertEquals(courses.get(8).getId(), topicsId[8]);
		assertEquals(courses.get(9).getId(), topicsId[9]);
		assertEquals(courses.get(10).getId(), topicsId[10]);
		assertEquals(courses.get(11).getId(), topicsId[11]);
		assertEquals(courses.get(12).getId(), topicsId[12]);
	}
	
	@Test
	public void test_getAllTopics() {
		// Given
		final Integer[] topicsId = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 };

		// When
		List<TopicEntity> topics = courseDao.getAllTopics();
		System.out.println("List of courses " + topics);

		// Then
		assertNotNull(topics);
		assertEquals(topics.size(), 22);
		assertEquals(topics.get(0).getId(), topicsId[0]);
		assertEquals(topics.get(1).getId(), topicsId[1]);
		assertEquals(topics.get(2).getId(), topicsId[2]);
		assertEquals(topics.get(3).getId(), topicsId[3]);
		assertEquals(topics.get(4).getId(), topicsId[4]);
		assertEquals(topics.get(5).getId(), topicsId[5]);
		assertEquals(topics.get(6).getId(), topicsId[6]);
		assertEquals(topics.get(7).getId(), topicsId[7]);
		assertEquals(topics.get(8).getId(), topicsId[8]);
		assertEquals(topics.get(9).getId(), topicsId[9]);
		assertEquals(topics.get(10).getId(), topicsId[10]);
		assertEquals(topics.get(11).getId(), topicsId[11]);
		assertEquals(topics.get(12).getId(), topicsId[12]);
		assertEquals(topics.get(13).getId(), topicsId[13]);
		assertEquals(topics.get(14).getId(), topicsId[14]);
		assertEquals(topics.get(15).getId(), topicsId[15]);
		assertEquals(topics.get(16).getId(), topicsId[16]);
		assertEquals(topics.get(17).getId(), topicsId[17]);
		assertEquals(topics.get(18).getId(), topicsId[18]);
		assertEquals(topics.get(19).getId(), topicsId[19]);
		assertEquals(topics.get(20).getId(), topicsId[20]);
		assertEquals(topics.get(21).getId(), topicsId[21]);
	}

	@Test
	public void test_getCourse_notPresent() {
		// Given
		final Integer courseId = -1;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertNull(courseEntity);
	}

	@Test
	public void test_insertCourse_withoutTopics() {
		// Given
		CourseEntity courseEntity = createCourse();

		// When
		courseDao.insert(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());

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
		courseDao.insert(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());

		// Then
		assertNotNull(courseEntity.getTopicById(topicEntity.getId()));
		assertEquals(courseEntityDb, courseEntity);
		assertEquals(courseEntityDb.getTopics(), courseEntity.getTopics());
	}

	@Test
	public void test_addTopic_forCourse() {
		// Given
		final Integer courseId = 208;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);
		TopicEntity topicEntity = createTopic(courseEntity);
		courseEntity.addTopic(topicEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertNotNull(courseEntityDb.getTopicById(topicEntity.getId()));
		assertEquals(courseEntityDb.getTopicById(topicEntity.getId()), topicEntity);
	}

	@Test
	public void test_deleteTopic_forCourse() {
		// Given
		final Integer courseId = 206;
		final Integer topicId = 15;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity.removeTopicById(topicId);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertNull(courseEntityDb.getTopicById(topicId));
	}

	@Test
	public void test_updateTopic_forCourse() {
		// Given
		final Integer courseId = 203;
		final Integer topicId = 10;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);
		TopicEntity topicEntity = courseEntity.getTopicById(topicId);
		updateTopic(topicEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(courseEntityDb.getTopicById(topicId), topicEntity);
	}

	@Test
	public void test_updateCourse_withoutTopics() {
		// Given
		final Integer courseId = 207;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity = updateCourse(courseEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
		assertEquals(courseEntityDb, courseEntity);
		assertEquals(0, courseEntity.getTopics().size());
		assertEquals(0, courseEntityDb.getTopics().size());
	}

	@Test
	public void test_updateCourse_withTopics() {
		// Given
		final Integer courseId = 202;

		// When
		CourseEntity courseEntity = courseDao.get(courseId);
		courseEntity = updateCourse(courseEntity);
		courseDao.update(courseEntity);
		CourseEntity courseEntityDb = courseDao.get(courseId);

		// Then
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
		boolean deleting = courseDao.delete(courseId);
		CourseEntity courseEntity = courseDao.get(courseId);

		// Then
		assertTrue(deleting);
		assertNull(courseEntity);
	}

	@Test
	public void test_deleteCourse_notPresent() {
		// Given
		final Integer courseId = -1;

		// When
		boolean deleting = courseDao.delete(courseId);

		// Then
		assertFalse(deleting);
	}

	@Test
	public void test_deleteCourse_WithTopics() {
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
	public void test_course_integrationTest_CRUD() {
		// 1. insert a new course
		CourseEntity courseEntity = createCourse();
		courseDao.insert(courseEntity);
		assertNotNull(courseEntity.getId());

		// 2. get the course from DB
		CourseEntity courseEntityDb = courseDao.get(courseEntity.getId());
		assertNotNull(courseEntityDb);
		assertEquals(courseEntityDb, courseEntity);

		// 3. Update the course in DB, and Get to check if updated correctly
		updateCourse(courseEntity);
		courseDao.update(courseEntity);
		courseEntityDb = courseDao.get(courseEntity.getId());
		assertEquals(courseEntityDb, courseEntity);

		// 4. Delete the course from DB, and Get to check if deleted correctly
		boolean isRemoved = courseDao.delete(courseEntity.getId());
		assertTrue(isRemoved);
		courseEntityDb = courseDao.get(courseEntity.getId());
		assertNull(courseEntityDb);
	}

	@Test
	public void test_topic_integrationTest_CRUD() {
		// 1. insert a new course
		CourseEntity courseEntity = createCourse();
		courseDao.insert(courseEntity);
		assertNotNull(courseEntity.getId());

		// 2. insert a new topic
		TopicEntity topicEntity = createTopic(courseEntity);
		courseDao.update(courseEntity);
		assertNotNull(topicEntity.getId());

		// 3. get the topic from DB
		TopicEntity topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertNotNull(topicEntityDb);
		assertEquals(topicEntityDb, topicEntity);

		// 4. Update the topic in DB, and Get to check if updated correctly
		updateTopic(topicEntity);
		courseDao.update(courseEntity);
		topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertEquals(topicEntityDb, topicEntity);

		// 5. Delete the topic from DB, and Get to check if deleted correctly
		courseEntity.removeTopicById(topicEntity.getId());
		courseDao.update(courseEntity);
		topicEntityDb = courseEntity.getTopicById(topicEntity.getId());
		assertNull(topicEntityDb);
	}
}
