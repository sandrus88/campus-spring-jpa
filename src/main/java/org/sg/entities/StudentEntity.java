package org.sg.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {
	
	@Id
    @Column(name = "student_id")
	private int id;
	
	private String name;
	private String surname;
	private String jobTitle;
	private String paymentType;
	private char sex;
	
	@OneToOne
    @PrimaryKeyJoinColumn
    private AddressEntity addressEntity;
	
	@ManyToMany
	private List<ExamEntity> exams;
	
	@ManyToMany
	@JoinTable(
			name = "subscriptions", 
			joinColumns = {@JoinColumn(name = "student_id")}, 
			inverseJoinColumns = {@JoinColumn(name = "course_id")}
			)
    private List<CourseEntity> courses;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public List<ExamEntity> getExams() {
		return exams;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public List<CourseEntity> getCourses() {
		return courses;
	}
}
