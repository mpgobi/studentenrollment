CREATE TABLE student(
   id number,
   name varchar2(80),
   PRIMARY KEY( id )
);

CREATE TABLE course(
   id number,
   name varchar2(80),
   PRIMARY KEY( id )
);

CREATE TABLE student_course(
   student_id number,
   course_id number,   
   PRIMARY KEY( student_id, course_id)
);