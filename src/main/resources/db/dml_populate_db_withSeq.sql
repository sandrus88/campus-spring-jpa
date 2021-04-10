
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (seq_student.nextval, 'Mario', 'Rossi', '', 'Confirmed', 'M');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (seq_student.nextval, 'Sandro', 'Gargano', 'Waiter', 'Confirmed', 'M');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (seq_student.nextval, 'Ermal', 'Aliraj', 'Web Developer', 'Confirmed', 'M');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (seq_student.nextval, 'Armela', 'Xhaxho', 'Shop Assistant', 'To be confirmed', 'F');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (seq_student.nextval, 'Aida', 'Xhaxho', 'Beauty Consultant', 'Not confirmed', 'F');

insert into course (ID, NAME, DESCRIPTION) values (seq_course.nextval, 'Java', 'Basic concepts and Java fundamentals');
insert into course (ID, NAME, DESCRIPTION) values (seq_course.nextval, 'Java advanced programming', '');
insert into course (ID, NAME, DESCRIPTION) values (seq_course.nextval, 'Operating Systems', 'This course will introduce you to modern operating systems');
insert into course (ID, NAME, DESCRIPTION) values (seq_course.nextval, 'Computer Network', '');
insert into course (ID, NAME, DESCRIPTION) values (seq_course.nextval, 'Javascript', 'Concetti base di Javascript');
insert into course (ID, NAME, DESCRIPTION) values (seq_course.nextval, 'XML', 'XMLSchema e parsing con Java');
insert into course (ID, NAME, DESCRIPTION) values (seq_course.nextval, 'Data Structures and Algorithms', '');

insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Objects Oriented Paradigm', 'OOPS concepts (Data Abstraction, Encapsulation, Inheritance, Polymorphism)', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Basic Java constructs like loops and data types', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'String handling', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Collection framework', 'List, ArrayList, LinkedList', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Exception handling', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Servlet', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'JSP', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'What is an Operating System?', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Operating System history', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Operating System concepts', 'CPU, file storage, input/output (I/O) devices, and network connections', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Operating System structures', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Local Area Network (LAN) Technologies', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Introduction of MAC Address', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Multiple Access Protocols', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Ethernet Frame Format', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Basic Data Structures', 'Arrays, Strings, Stacks, Queues', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Basic math operations', 'addition, subtraction, multiplication, division, exponentiation', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Euclid s GCD Algorithm', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Greedy Algorithms', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Binary Searching', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Multithreading', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (seq_topic.nextval, 'Programmazione di stringa', '', null);
