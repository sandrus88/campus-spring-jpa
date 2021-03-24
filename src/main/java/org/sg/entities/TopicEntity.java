package org.sg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TOPIC")
public class TopicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTopic")
	@SequenceGenerator(name = "seqTopic", sequenceName = "SEQ_TOPIC", initialValue = 200, allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID")
	private CourseEntity courseEntity;

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

	public void setCourseEntity(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof TopicEntity)) {
			return false;
		}
		TopicEntity other = (TopicEntity) o;
		if (id != other.id) {
			return false;
		}
		if (name != null && !name.equals(other.name)) {
			return false;
		}
		if (description != null && !description.equals(other.description)) {
			return false;
		}
		if (courseEntity != null && !courseEntity.equals(other.courseEntity)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = result + ((name == null) ? 0 : name.hashCode());
		result = result + ((description == null) ? 0 : description.hashCode());
		result = result + ((courseEntity == null) ? 0 : courseEntity.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Topic  [id: " + id + ", name: " + name + ", description: " + description + ", id course: " + courseEntity.getId() + "]";
	}
}