package com.statestreet.studentmgmt.entity.student;

import javax.persistence.*;
import com.statestreet.studentmgmt.entity.course.Course;

import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    //Cascade is done only for the persist. The delete is handled separatedly, in order to avoid multiple 
    //delete queries created by the Hibernate 
	//Lazy intialization is declared here. But the Join Fetch is used for this exercise    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_course",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    // additional attributes getters and setters, equals(), toString()
}
