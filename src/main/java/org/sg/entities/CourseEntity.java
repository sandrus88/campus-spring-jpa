package org.sg.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class CourseEntity {
	
	@Id
    @Column(name = "course_id")
	private int id;
	
	private String name;
	private String description;
	
	@OneToMany
	private List<TopicEntity> topics;
	
	@ManyToMany
	private List<ExamEntity> exams;
	
	@ManyToMany
	private List<StudentEntity> students;
	
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
	public List<TopicEntity> getTopics() {
		return topics;
	}
	public List<ExamEntity> getExams() {
		return exams;
	}
	public List<StudentEntity> getStudents() {
		return students;
	}
}
