package org.sg.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqStudent")
	@SequenceGenerator(name="seqStudent", sequenceName = "SEQ_STUDENT", allocationSize = 1)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SURNAME")
	private String surname;
	@Column(name = "JOB_TITLE")
	private String jobTitle;
	@Column(name = "PAYMENT_TYPE")
	private String paymentType;
	@Column(name = "SEX")
	private char sex;
	
//	@OneToOne
//    @PrimaryKeyJoinColumn
//    private AddressEntity addressEntity;
//	
//	@OneToMany
//	private List<ExamEntity> exams;
//	
//	@ManyToMany
//	@JoIntegerable(
//			name = "subscriptions", 
//			joinColumns = {@JoinColumn(name = "STUDENT_ID")}, 
//			inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")}
//			)
//    private List<CourseEntity> courses;

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

//	public List<ExamEntity> getExams() {
//		return exams;
//	}
//
//	public AddressEntity getAddressEntity() {
//		return addressEntity;
//	}
//
//	public List<CourseEntity> getCourses() {
//		return courses;
//	}
	
	@Override
	public String toString() {
		return "Student  [id: " + id + ", name: " + name + ", surname: " + surname
				+ ", job title: " + jobTitle + ", paymentType: " + paymentType + ", sex: " + sex
				+ "]";
	}
}
