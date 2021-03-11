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
    @Column(name = "exam_id")
	private int id;
	
	private Date examDate;
	private int mark;
	private int postalCode;
	private int studentId;
	private int courseId;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getStudentId() {
		studentId = studentEntity.getId();
		return studentId;
	}

	public int getCourseId() {
		courseId = courseEntity.getId();
		return courseId;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}
}
