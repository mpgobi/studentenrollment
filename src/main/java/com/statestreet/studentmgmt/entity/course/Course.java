package com.statestreet.studentmgmt.entity.course;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //Order by is used to default the Student.name. Set is used for Hibernate performance improvment
	//Lazy intialization is declared here. But the Join Fetch is used for this exercise
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @OrderBy("name")
    private Set<Student> students = new HashSet<>();

    public Course() {
    }

    public Course(String name) {
        this.name= name;
    }

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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

    // additional attributes getters and setters, equals(), toString()
}