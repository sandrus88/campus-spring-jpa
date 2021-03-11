package org.sg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class TopicEntity {
	
	@Id
    @Column(name = "topic_id")
	private int id;
	
	private String name;
	private String description;
	private int courseId;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private CourseEntity courseEntity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCourseId() {
		courseId = courseEntity.getId();
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public CourseEntity getCourseEntity() {
		return courseEntity;
	}
}