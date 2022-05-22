For this exercise I am planning to use Spring Boot with JPA. Springboot provides all the dependency build, starter, transactions, auto config..etc.
Spring Boot project have a spring-data-* or spring-tx dependencies on the classpath, then transaction management will be enabled.
PLEASE NOTE:  This is not tested code. this may still can have complitation error. Let me know, if you looking for tested code with test cases.


1) DDL is provided in  studentenrollment.sql file
1.1) Assume there are only couple of elements in each table Student and Course.
1.2) student_course table use is to maintain the join between student and course. Assume the volume of the application is not huge.we will go with relational database with normalized table.


Bonus Points-1 :
---------------

There are multiple ways to look after adding the new StudentCourse with additional fields. The RDBMS tablesstudent_course will have have 
OneToMany between student and student_course and OneToMany relational between Course and student_course. This represent the true relationship of the RDBMS.

Approach 1: student_course will have student_id and the course_id as primary key.
Approach 2(improvised): The student_course table will have it's own Primary Key ID and 

1) Add the score in the student_course and create StudentCourse entity
2) The new entity StudentCourse will have the ID(primary key) , student_id, course_id. Additional field score and etc..
3) StudentCourse will have ManyToOne relationship with the Student and Course

@Entity
class StudentCourse {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;    

    double score;
    
    // additional attributesstandard constructors, getters, and setters
}

4) Student Entity will have OneToMany relationship with StudentCourse

 @OneToMany(mappedBy = "student")
 Set<StudentCourse> scores;

5) Course Entity will have OneToMany relationship with StudentCourse
 
@OneToMany(mappedBy = "course")
Set<StudentCourse> scores;



Bonus Points-2 : Implemented findAllStudentsNotRegisteredOnCourse method
---------------

Hibernate Best practices for Many-To-Many
-----------------------------------------
1) Data type using Set vs. List. -  Many to Many with List is inefficient in Hibernate 	 
2) Association here is handled with joining table student_course. 
3) Fetch Type Lazy is best of ManyToMany for performance and use use query specific fetching top avoid hibernate N+1 query issue. This can be done with Join Fetch(which is used in this example) 	
4) Cascade is costly and used only for the PERSIST. Cascade.ALL lower the performance. Cascade.Remove may remove unexpected data(test it)
Cascade.PERSIST can also be avoided if required.
5) Always log all the Hibernate/JPA generated SQL and validate (1) if more or unexpected sql generated (2) Check those SQL performace can be improved.

