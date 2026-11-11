package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table (name="Student")
public class Student {

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String first_name;
  private String last_name;
  private String email;

  @ManyToMany(mappedBy = "students",
    fetch=FetchType.LAZY,
    cascade = {CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;


  public Student(){
  }

  public Student(String first_name, String last_name, String email){
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
  }

  public String getFirstName(){
    return first_name;
  }

  public String getLastName(){
    return last_name;
  }

  public String getEmail(){
    return email;
  }

  public void setFirstName(String first_name){
    this.first_name = first_name;
  }

  public void setLastName(String last_name){
    this.last_name = last_name;
  }

  public void setEmail(String email){
    this.email = email;
  }

@Override
public String toString() {
  return "Student ID: " + id + 
          "\n First Name: " + first_name +
          "\n Last Name: " + last_name +
          "\n Email: " + email;
}


public void addCourse(Course course){
  if(courses == null){
    courses = new ArrayList<>();
  }
  courses.add(course);
  course.addStudent(this);
}

public List<Course> getCourses() {
  return this.courses;
}

}
