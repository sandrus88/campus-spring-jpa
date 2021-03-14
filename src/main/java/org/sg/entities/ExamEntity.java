package org.sg.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exams")
public class ExamEntity {
	
	@Id
    @Column(name = "ID")
	private Integer id;
	@Column(name = "EXAM_DATE")
	private Date examDate;
	@Column(name = "MARK")
	private int mark;
	@Column(name = "POSTAL_CODE")
	private int postalCode;
	
	@ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private CourseEntity courseEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}
}
