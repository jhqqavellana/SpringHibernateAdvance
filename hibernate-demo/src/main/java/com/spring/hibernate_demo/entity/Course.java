package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table (name = "course")
public class Course {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private int id;

@Column(name="title")
private String title;

@ManyToOne (cascade = {CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.PERSIST,
                        CascadeType.REFRESH})
@JoinColumn (name = "instructor_id")
private Instructor instructor;

@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "course_id")
private List<Review> review;

public Course(){
}

public Course(String title){
  this.title = title;
}

public Instructor setInstructor(Instructor instructor2) {
  return this.instructor = instructor2;
}

public void addReview(Review rev){
  if(review == null){
    review = new ArrayList<>();
  }
  review.add(rev);
}

public List<Review> getReview(){
  return review;
}

@Override
public String toString() {
  return "Course [id=" + id + ", title=" + title + "]";
}


}
