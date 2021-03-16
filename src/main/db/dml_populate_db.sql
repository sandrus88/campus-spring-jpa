
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (01, 'Mario', 'Rossi', '', 'Confirmed', 'M');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (02, 'Sandro', 'Gargano', 'Waiter', 'Confirmed', 'M');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (03, 'Ermal', 'Aliraj', 'Web Developer', 'Confirmed', 'M');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (04, 'Armela', 'Xhaxho', 'Shop Assistant', 'To be confirmed', 'F');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (05, 'Aida', 'Xhaxho', 'Beauty Consultant', 'Not confirmed', 'F');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (06, 'Andrea', 'Bonfanti', 'Engineer', 'To be confirmed', 'M');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (07, 'Francesca', 'Morbillo', 'Lawyer', 'Confirmed', 'F');
insert into student (ID, NAME, SURNAME, JOB_TITLE, PAYMENT_TYPE, SEX) values (08, 'Penelope', 'Fatima', 'Project Manager', 'Not confirmed', 'F');

insert into course (ID, NAME, DESCRIPTION) values (201, 'Java', 'Basic concepts and Java fundamentals');
insert into course (ID, NAME, DESCRIPTION) values (202, 'Java advanced programming', '');
insert into course (ID, NAME, DESCRIPTION) values (203, 'Operating Systems', 'This course will introduce you to modern operating systems');
insert into course (ID, NAME, DESCRIPTION) values (204, 'Computer Network', '');
insert into course (ID, NAME, DESCRIPTION) values (205, 'Javascript', 'Concetti base di Javascript');
insert into course (ID, NAME, DESCRIPTION) values (206, 'XML', 'XMLSchema e parsing con Java');
insert into course (ID, NAME, DESCRIPTION) values (207, 'Data Structures and Algorithms', '');

insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (301, 'Objects Oriented Paradigm', 'OOPS concepts (Data Abstraction, Encapsulation, Inheritance, Polymorphism)', 201);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (302, 'Basic Java constructs like loops and data types', '', 201);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (303, 'String handling', '', 201);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (304, 'Collection framework', 'List, ArrayList, LinkedList', 201);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (305, 'Exception handling', '', 201);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (306, 'Servlet', '', 201);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (307, 'JSP', '', 201);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (308, 'What is an Operating System?', '', 203);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (309, 'Operating System history', '', 203);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (310, 'Operating System concepts', 'CPU, file storage, input/output (I/O) devices, and network connections', 203);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (311, 'Operating System structures', '', 203);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (312, 'Local Area Network (LAN) Technologies', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (313, 'Introduction of MAC Address', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (314, 'Multiple Access Protocols', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (315, 'Ethernet Frame Format', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (316, 'Basic Data Structures', 'Arrays, Strings, Stacks, Queues', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (317, 'Basic math operations', 'addition, subtraction, multiplication, division, exponentiation', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (318, 'Euclid s GCD Algorithm', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (319, 'Greedy Algorithms', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (320, 'Binary Searching', '', null);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (321, 'Multithreading', '', 202);
insert into topic (ID, NAME, DESCRIPTION, COURSE_ID) values (322, 'Programmazione di stringa', '', 202);

insert into subscriptions (STUDENT_ID, COURSE_ID) values (01, 201);
insert into subscriptions (STUDENT_ID, COURSE_ID) values (02, 201);
insert into subscriptions (STUDENT_ID, COURSE_ID) values (03, 201);
insert into subscriptions (STUDENT_ID, COURSE_ID) values (02, 207);
insert into subscriptions (STUDENT_ID, COURSE_ID) values (03, 207);
insert into subscriptions (STUDENT_ID, COURSE_ID) values (04, 207);
insert into subscriptions (STUDENT_ID, COURSE_ID) values (05, 207);

insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 001', TO_DATE('14/10/2020', 'DD/MM/YYYY'), 25, 02, 201);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 002', TO_DATE('17/12/2020', 'DD/MM/YYYY'), 24, 02, 201);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 003', TO_DATE('01/02/2021', 'DD/MM/YYYY'), 20, 02, 205);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 004', TO_DATE('12/09/2020', 'DD/MM/YYYY'), 30, 03, 201);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 005', TO_DATE('13/12/2020', 'DD/MM/YYYY'), 30, 03, 205);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 006', TO_DATE('22/01/2021', 'DD/MM/YYYY'), 15, 04, 202);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 007', TO_DATE('22/01/2021', 'DD/MM/YYYY'), 17, 04, 202);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 008', TO_DATE('22/01/2021', 'DD/MM/YYYY'), 28, 04, 202);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 009', TO_DATE('15/11/2020', 'DD/MM/YYYY'), 16, 01, 203);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 010', TO_DATE('15/11/2020', 'DD/MM/YYYY'), 28, 01, 203);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 011', TO_DATE('02/11/2020', 'DD/MM/YYYY'), 25, 05, 202);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 012', TO_DATE('15/11/2020', 'DD/MM/YYYY'), 17, 05, 201);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 013', TO_DATE('03/05/2020', 'DD/MM/YYYY'), 15, 01, 201);
insert into exams (ID, EXAM_DATE, MARK, STUDENT_ID, COURSE_ID) values ('Exm 014', TO_DATE('18/11/2020', 'DD/MM/YYYY'), 14, 01, 205);

insert into address (STUDENT_ID, STREET, BUILDING_NUMBER, POSTAL_CODE, CITY, PROVINCE_CODE) values (01, '', '', 50122, 'Firenze', 'FI');
insert into address (STUDENT_ID, STREET, BUILDING_NUMBER, POSTAL_CODE, CITY, PROVINCE_CODE) values (02, 'Via del ponte di mezzo', '42', 50127, 'Firenze', 'FI');
insert into address (STUDENT_ID, STREET, BUILDING_NUMBER, POSTAL_CODE, CITY, PROVINCE_CODE) values (03, 'Rue du Belgique', '63/A', 60198, 'Bruxelles', '');
