package org.sg.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_student")
	@SequenceGenerator(name="seq_student")
    @Column(name = "ID")
	private int id;
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
	
	@OneToOne
    @PrimaryKeyJoinColumn
    private AddressEntity addressEntity;
	
	@OneToMany
	private List<ExamEntity> exams;
	
	@ManyToMany
	@JoinTable(
			name = "subscriptions", 
			joinColumns = {@JoinColumn(name = "ID")}, 
			inverseJoinColumns = {@JoinColumn(name = "ID", insertable = false, updatable = false)}
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
	
	@Override
	public String toString() {
		return "Student  [id: " + id + ", name: " + name + ", surname: " + surname
				+ ", job title: " + jobTitle + ", paymentType: " + paymentType + ", sex: " + sex
				+ "]";
	}
}
