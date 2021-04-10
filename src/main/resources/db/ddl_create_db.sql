-- creare uno script dove sono elencati i drop table e create table

create table course (    
    ID number, 
    NAME varchar2(100 byte) not null, 
    DESCRIPTION varchar2(500 byte),
        
    PRIMARY KEY (ID)
);
 
create table student (	
    ID number, 
    NAME varchar2(100 byte) not null, 
    SURNAME varchar2(100 byte) not null,
    JOB_TITLE varchar2(100 byte),
    PAYMENT_TYPE varchar2(100 byte),
    SEX	char(1 byte) not null,
        
    PRIMARY KEY (ID)
);
 
create table topic (	
    ID number, 
    NAME varchar2(100 byte) not null, 
    DESCRIPTION varchar2(500 byte),
    COURSE_ID number,
        
    PRIMARY KEY (ID),
    CONSTRAINT topic_course_fk FOREIGN KEY (COURSE_ID) REFERENCES course(ID)
);
  
create table subscriptions (   
    STUDENT_ID number,
    COURSE_ID number,
        
    PRIMARY KEY (STUDENT_ID, COURSE_ID),
    CONSTRAINT student_to_course_fk FOREIGN KEY (STUDENT_ID) REFERENCES student(ID),
    CONSTRAINT course_to_student_fk FOREIGN KEY (COURSE_ID) REFERENCES course(ID)
);
    
create table exams (
    ID number,
    EXAM_DATE date,
    MARK number,
    STUDENT_ID number,
    COURSE_ID number,
        
    PRIMARY KEY (ID),
    CONSTRAINT exam_student_fk FOREIGN KEY (STUDENT_ID) REFERENCES student(ID),
    CONSTRAINT exam_course_fk FOREIGN KEY (COURSE_ID) REFERENCES course(ID)
);
    
create table address (
    STUDENT_ID number,
    STREET varchar2(100 byte),
    NR varchar2(100 byte),
    POSTAL_CODE number,
    CITY varchar2(100 byte),
    PROVINCE_CODE varchar2(2 byte),
    
    PRIMARY KEY (STUDENT_ID),
    CONSTRAINT address_student_fk FOREIGN KEY (STUDENT_ID) REFERENCES student(ID)
);
    
create sequence seq_student
    START WITH 1000
    INCREMENT BY 1;
        
create sequence seq_course
    START WITH 1000
    INCREMENT BY 1;
        
create sequence seq_topic
    START WITH 1000
    INCREMENT BY 1;
    
create sequence seq_exam
    START WITH 1000
    INCREMENT BY 1;
    
create view vw_course_topics
as
select c.name courseName, count(*) nrTopics 
from topic t
    right join course c 
    on t.course_id = c.id
group by c.name;
    
create view vw_course_students
as
select c.name courseName, count(*) nrStudentsSignedUp 
from subscriptions sub
    right join student s 
    on sub.student_id = s.id
    right join course c 
    on sub.course_id = c.id 
group by c.name;
    
create view vw_course_details
as 
select vwc.coursename, vwt.nrtopics, vwc.nrStudentsSignedUp 
from (
select c.name courseName, count(*) nrStudentsSignedUp 
from subscriptions sub
    right join student s 
    on sub.student_id = s.id
    right join course c 
    on sub.course_id = c.id
group by c.name
) vwc 
    inner join (
select c.name courseName, count(*) nrTopics 
from topic t
    right join course c 
    on t.course_id = c.id
group by c.name 
) vwt 
    on vwt.coursename = vwc.coursename;