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
        ID varchar2(100 byte),
        EXAM_DATE date,
        MARK number,
        STUDENT_ID number,
        COURSE_ID number,
        
        PRIMARY KEY (ID),
        CONSTRAINT exam_student_fk FOREIGN KEY (STUDENT_ID) REFERENCES student(ID),
        CONSTRAINT exam_course_fk FOREIGN KEY (COURSE_ID) REFERENCES course(ID)
    );
    