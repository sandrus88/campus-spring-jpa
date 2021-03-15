package org.sg.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXAMS")
public class ExamEntity {
	
	@Id
    @Column(name = "ID")
	private Integer id;
	@Column(name = "EXAM_DATE")
	private Date examDate;
	@Column(name = "MARK")
	private Integer mark;
	
	@ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
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

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof ExamEntity)) {
			return false;
		}
		ExamEntity other = (ExamEntity) o;
		if (id != other.id) {
			return false;
		}
		if (examDate != null && !examDate.equals(other.examDate)) {
			return false;
		}
		if (mark != null && !mark.equals(other.mark)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = result + ((examDate == null) ? 0 : examDate.hashCode());
		result = result + ((mark == null) ? 0 : mark.hashCode());
		return result;
	}
}
