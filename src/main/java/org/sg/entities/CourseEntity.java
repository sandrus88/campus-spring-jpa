package org.sg.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCourse")
	@SequenceGenerator(name="seqCourse", sequenceName = "SEQ_COURSE", initialValue = 200, allocationSize = 1)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy = "courseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TopicEntity> topics;
	
	@OneToMany(mappedBy = "courseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExamEntity> exams;
	
	@ManyToMany(mappedBy = "courses")
	private List<StudentEntity> students;
	
	public CourseEntity(){
		students = new ArrayList<>();
	}
	
	public void addStudent(StudentEntity studentEntity) {
		students.add(studentEntity);
	}
	
	public void removeStudent(StudentEntity studentEntity) {
		students.remove(studentEntity);
	}
	
	public void addTopic(TopicEntity topic) {
		topics.add(topic);
	}

	public TopicEntity getTopicById(Integer topicId) {
		TopicEntity topicEntity = null;
		for (TopicEntity topic : topics) {
			if (topicId == topic.getId()) {
				topicEntity = topic;
			}
		}
		return topicEntity;
	}

	public void removeTopicById(Integer topicId) {
		TopicEntity topicEntity = getTopicById(topicId);
		topics.remove(topicEntity);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public void setTopics(List<TopicEntity> topics) {
		this.topics = topics;
	}
	
	public List<TopicEntity> getTopics() {
		return topics;
	}
	
	public void setExams(List<ExamEntity> exams) {
		this.exams = exams;
	}
	
	public List<ExamEntity> getExams() {
		return exams;
	}
	
	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	public List<StudentEntity> getStudents() {
		return students;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof CourseEntity)) {
			return false;
		}
		CourseEntity other = (CourseEntity) o;
		if (id != other.id) {
			return false;
		}
		if (name != null && !name.equals(other.name)) {
			return false;
		}
		if (description != null && !description.equals(other.description)) {
			return false;
		}
		if (exams != null && !exams.equals(other.exams)) {
			return false;
		}
		if (topics != null && !topics.equals(other.topics)) {
			return false;
		}
		if (students != null && !students.equals(other.students)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = result + ((name == null) ? 0 : name.hashCode());
		result = result + ((description == null) ? 0 : description.hashCode());
		result = result + ((exams == null) ? 0 : exams.hashCode());
		result = result + ((topics == null) ? 0 : topics.hashCode());
		result = result + ((students == null) ? 0 : students.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Course  [id: " + id + ", name: " + name + ", description: " + description + "]";
	}
}
