--seleziona tutte le colonne della tabella 
select * from topic;
select * from student;
select * from address;
select * from course;
select * from subscriptions;
select * from exams;
select SEQ_STUDENT.nextval from dual;

-- tutti i topics del corso 201
select * from topic t where course_id = 201;
update t set course_id = 201
where id in(301, 302, 303, 304, 305, 306, 307); 

-- dammi il corso di nome "java"
select * from course where name = 'Java';
-- dammi i corsi che contengono nel nome la stringa "java"
select * from course where upper(name) like upper('%java%');

-- tutti i topics del corso di nome "java"
select * 
from topic t
inner join course c on c.id = t.course_id
where c.name = 'Java';

-- tutti i topics del corso con nome contenente la stringa "java"
select t.*, c.name 
from topic t 
inner join course c on c.id = t.course_id
where upper(c.name) like upper('%java%');

-- lista di tutti gli studenti associati a qualche corso
select * 
from subscriptions sub
inner join student s on sub.student_id = s.id
inner join course c on sub.course_id = c.id;
      
-- numero di studenti registrati al corso 201
select count(*) 
from subscriptions sub
inner join student s on sub.student_id = s.id
inner join course c on sub.course_id = c.id
where c.id = 201;

-- in quanti corsi e' iscritto sandro
select count(*) 
from subscriptions sub
inner join student s on sub.student_id = s.id
inner join course c on sub.course_id = c.id
where s.name = 'Sandro';

-- in quali corsi e' iscritto Armela
select c.name
from subscriptions sub
inner join student s on sub.student_id = s.id
inner join course c on sub.course_id = c.id
where s.name = 'Armela';

-- registrazione aida al corso 201
insert into subscriptions (STUDENT_ID, COURSE_ID) values (05, 201); 

-- cancellazione corso 203
update topic t set t.course_id = null
where t.course_id = 203;
delete from course c
where c.id = 203;

-- tutti gli esami dello studente col nome ermal
select ex.*, s.name
from exams ex
inner join student s on ex.student_id = s.id
where s.name = 'Ermal';

-- tutti gli esami del corso 201
select ex.*, c.id
from exams ex
inner join course c on ex.course_id = c.id
where c.id = 201;

-- tutti gli studenti che non hanno superato l'esame del corso con nome java
select ex.*, c.name, s.name
from exams ex
inner join student s on ex.student_id = s.id
inner join course c on ex.course_id = c.id
where upper(c.name) = upper('java')
and ex.mark < 18;

-- numero di studenti registrati per ogni corso
select c.name courseName, count(*) nrStudentsSignedUp 
from subscriptions sub
right join student s on sub.student_id = s.id
right join course c on sub.course_id = c.id
group by c.name;

-- numero di topic per ogni corso corso
select c.name courseName, count(*) nrTopics 
from topic t
right join course c on t.course_id = c.id
group by c.name;

select * from vw_course_students;
select * from vw_course_topics;

select * 
from vw_course_students vwc
inner join vw_course_topics vwt on vwt.coursename = vwc.coursename;

select vwc.coursename, vwt.nrtopics, vwc.nrStudentsSignedUp 
from (
    select c.name courseName, count(*) nrStudentsSignedUp 
    from subscriptions sub
        right join student s on sub.student_id = s.id
        right join course c on sub.course_id = c.id
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

--seleziona la colonna name della tabella student2 con i soli campi che hanno il valore del campo surname Xhaxho 
select name from student
where surname = 'Xhaxho';

--seleziona la colonna description della tabella course2
select description from course;

--seleziona la colonna name della tabella course2 con i soli campi che hanno il valore del campo description non vuoto 
select name from course
where description is not null;

--seleziona i topic compresi tra l'id 301 e il 304 
select * from topic
where id between 301 and 304;




