package com.statestreet.studentmgmt.service;

import java.util.List;

import com.statestreet.studentmgmt.entity.student.Student;
import com.statestreet.studentmgmt.entity.student.StudentRepository;

/**
 * This is service class to call Student and Course
 * This class can be further split based on the requirments. 
 */
public class CollegeEnrollmentService {

    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    CourseRepository courseRepository;
    
    //2.3 Get all studentssortedby name with readonly operation for improved performance
    @Transactional(readOnly = true)
    public List<Student> findAllStudentsRegisteredOnCourse(String courseName){
    	return studentRepository.findAllStudentsRegisteredOnCourse(courseName);
    }
    
    //2.1 : Add Student with Course. If new course, then add into course
    //else find the course and update the student.
    public void addStudent(Student student, List<Course> courses){
    	studentRepository.save(student);
    	//Need to add check. if these are new course then save those courses
    	//courseRepository.saveAll(courses);
    	// add courses to the student
    	student.getCourses().addAll(courses);
    	//update the student
    	studentRepository.save(student);
    }
    
    //2.5 Get all students , not registered in the given course
    @Transactional(readOnly = true)
    public List<Student> findAllStudentsNotRegisteredOnCourse(String courseName){
    	return studentRepository.findAllStudentsNotRegisteredOnCourse(courseName);
    }

    
    //2.2 : Delete Student by id. Not done using CASCADE.
    public void deleteStudent(Long studentId){
		//Add code to delete student_course before delete the student
		//This could be native SQL or once the student_Course entity is defined, add delete here.
    	studentRepository.deleteById(studentID);    	
    }
}
