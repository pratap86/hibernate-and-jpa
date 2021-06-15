insert into course(id, name, created_date, last_updated_date, is_deleted) values
(10001, 'Hibernate in 100 steps', sysdate(), sysdate(), false),
(10002, 'Spring in 100 steps', sysdate(), sysdate(), false),
(10003, 'Microservices', sysdate(), sysdate(), false),
(10004, 'Docker', sysdate(), sysdate(), false),
(10005, 'Kubernetes', sysdate(), sysdate(), false);

insert into passport(id, passport_number) values
(40001, 'E123456'),
(40002, 'N123457'),
(40003, 'L123558');

insert into student(id, name, passport_id) values
(20001, 'Sankalp', 40001),
(20002, 'Rahul', 40002),
(20003, 'Ravi', 40003);

insert into review(id, rating, description, course_id) values
(50001, 5, 'Great Course', 10001),
(50002, 4, 'Wonderful Course', 10003),
(50003, 5, 'Awesome Course', 10001);

insert into STUDENT_COURSE(student_id, course_id) values
(20001, 10002),
(20002, 10002),
(20003, 10002),
(20001, 10003);

insert into part_time_employee (id, name, hourly_wage) values
(60001, 'Naresh', 60),
(60002, 'Prakash', 70);

insert into full_time_employee (id, name, salary) values
(60001, 'Ram', 60000),
(60002, 'Shyam', 70000),
(60003, 'Shankar', 65000);

