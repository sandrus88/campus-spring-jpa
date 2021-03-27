package org.sg.entities;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "EXAMS")
public class ExamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqExam")
	@SequenceGenerator(name="seqExam", sequenceName = "SEQ_EXAM", initialValue = 200, allocationSize = 1)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "EXAM_DATE")
	private Date examDate;
	@Column(name = "MARK")
	private Integer mark;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private StudentEntity studentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
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
	
	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
	
	public StudentEntity getStudentEntity() {
		return studentEntity;
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
		if (studentEntity != null && !studentEntity.equals(other.studentEntity)) {
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
		result = result + ((examDate == null) ? 0 : examDate.hashCode());
		result = result + ((mark == null) ? 0 : mark.hashCode());
		result = result + ((studentEntity == null) ? 0 : studentEntity.hashCode());
		result = result + ((courseEntity == null) ? 0 : courseEntity.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Exam  [id: " + id + ", date of exam: " + examDate + ", mark: " + mark + ", id student: " + (studentEntity != null ? studentEntity.getId() : "null") + ", id course: " + (courseEntity != null ? courseEntity.getId() : "null") + "]";
	}
}
