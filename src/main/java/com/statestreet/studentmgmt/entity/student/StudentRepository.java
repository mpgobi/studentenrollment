package com.statestreet.studentmgmt.entity.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statestreet.studentmgmt.*;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // This is the JPQL query get all students for given course and order by student name 
	// JOIN here will return only the Student data
    @Query("SELECT s FROM Student s JOIN s.course c WHERE c.name = ?1 ORDER BY s.name")
    List<Student> findAllStudentsRegisteredOnCourse(String name);

	//Idea is using the JPQL subquery to retrive all students , not registered in the given course.This SQL/method yet to be tested
	@Query("SELECT distinct s FROM Student s where  NOT EXISTS ( select id from course c WHERE c.student = student and name = ?1 ) ")	
	List<Student> findAllStudentsNotRegisteredOnCourse(String name);
}
