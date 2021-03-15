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
	@SequenceGenerator(name = "seqStudent", sequenceName = "SEQ_STUDENT", allocationSize = 1)
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
	private Character sex;

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

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
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
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof StudentEntity)) {
			return false;
		}
		StudentEntity other = (StudentEntity) o;
		if (id != other.id) {
			return false;
		}
		if (name != null && !name.equals(other.name)) {
			return false;
		}
		if (surname != null && !surname.equals(other.surname)) {
			return false;
		}
		if (jobTitle != null && !jobTitle.equals(other.jobTitle)) {
			return false;
		}
		if (paymentType != null && !paymentType.equals(other.paymentType)) {
			return false;
		}
		if (sex != null && !sex.equals(other.sex)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = result + ((name == null) ? 0 : name.hashCode());
		result = result + ((surname == null) ? 0 : surname.hashCode());
		result = result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Student  [id: " + id + ", name: " + name + ", surname: " + surname + ", job title: " + jobTitle
				+ ", paymentType: " + paymentType + ", sex: " + sex + "]";
	}
}
