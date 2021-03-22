package org.sg.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCourse")
	@SequenceGenerator(name="seqCourse", sequenceName = "SEQ_COURSE", allocationSize = 1)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	
//	@OneToMany
//	private List<TopicEntity> topics;
//	
	@OneToMany(mappedBy = "courseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExamEntity> exams;
	
//	@ManyToMany
//	private List<StudentEntity> students;
	
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
//	public List<TopicEntity> getTopics() {
//		return topics;
//	}
	
	public void setExams(List<ExamEntity> exams) {
		this.exams = exams;
	}
	
	public List<ExamEntity> getExams() {
		return exams;
	}
//	public List<StudentEntity> getStudents() {
//		return students;
//	}
	
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
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = result + ((name == null) ? 0 : name.hashCode());
		result = result + ((description == null) ? 0 : description.hashCode());
		result = result + ((exams == null) ? 0 : exams.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Course  [id: " + id + ", name: " + name + ", description: " + description + "]";
	}
}
