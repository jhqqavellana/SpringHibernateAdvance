package com.spring.hibernate_demo;

import org.springframework.boot.SpringApplication;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(HibernateDemoApplication.class, args);
  }
  @Bean
  CommandLineRunner commandLineRunner(AppDao app) {
    return runner -> {
      // createInstructor(app);
      // findInstructor(app);
      // deleteInstructor(app);
      // findInstructorDetail(app);
      // deleteInstructorDetail(app);
      // createInstructorWithCourses(app);
      // findInstructorWithCourses(app);
      // findInstructorWithCoursesJoinFetch(app);
      // updateInstructor(app);
      // deleteInstructorOnly(app);
      // createCourseAndReview(app);
      // retrieveCourseAndReviews(app);
      // deleteCourseAndreviews(app);
      // createCourseAndStudents(app);
      // findCourseAndStudentByCourseId(app);
      // findStudentAndCourse(app);
      // addMoreCoursesForStudent(app);
      // deleteCourseById(app);
      deleteStudent(app);
    };
  }


  private void findCoursesforInstructor(AppDao app) {
    int id = 22;
    System.out.println("Find instructor id: " + id);
  }


  private void findInstructorDetail(AppDao app) {
    int id = 7;
    System.out.println("Find id: " + id);
    InstructorDetail inDeets = app.findInstructorDetailById(id);
    System.out.println("Details: " + inDeets);
    System.out.println("Instructor: " + inDeets.getInstructor());
  }


  private void deleteInstructor(AppDao app) {
    int id = 1;
    System.out.println("Delete id: " + id);
    app.deleteInstructorById(id);
    System.out.println("Deleted successfully!");
  }


  private void findInstructor(AppDao app) {
    int id = 8;
    System.out.println("Find id: " + id);
    
    Instructor ins = app.findInstructorById(id);
    
    System.out.println("Instructor: " + ins);
    System.out.println("Details: " + ins.getInstructorDetail());
  }


  private void createInstructor(AppDao app) {
    Instructor ins = new Instructor("Keanu",
                                    "Andal",
                                        "keanu.andal@email.com");

    InstructorDetail deets = new InstructorDetail("Esports",
                                                  "ML");

    ins.setInstructorDetail(deets);

    System.out.println("Information saved!");
    app.save(ins);
  }


  private void deleteInstructorDetail(AppDao app) {
      int id = 6;
      System.out.println("Delete id:" + id);
      app.deleteInstructorDetail(id);
      System.out.println("Deleted Successfully!");
  }


  private void createInstructorWithCourses(AppDao app) {
    Instructor ins = new Instructor("Peri",
                                      "Winkle",
                                        "peri.winkleu@email.com");
    InstructorDetail deets = new InstructorDetail("Science",
                                                    "Sprinkling dust");
    ins.setInstructorDetail(deets);
    Course course1 = new Course("Physics");
    Course course2 = new Course("Biology");
    ins.add(course1);
    ins.add(course2);
    System.out.println("Courses saved: " + ins.getCourses());
    app.save(ins);
  }


  private void findInstructorWithCourses(AppDao app) {
  int id = 1;
  System.out.println("Find instructor id: " + id);
    
  Instructor ins = app.findInstructorById(id);
    
  System.out.println("Instructor: " + ins);
  System.out.println("Courses: " + ins.getCourses());
  }


  private void findCourseForInstructor(AppDao app){
    int id = 24;

    System.out.println("Find instructor id:" + id);
    Instructor ins = app.findInstructorById(id);
    System.out.println("Instructor: " + ins);

    System.out.println("Courses form id: " + id);
    List<Course> courses = app.findCoursesByInstructorId(id);

    ins.setCourses(courses);
    System.out.println(ins.getCourses());
  }


  private void findInstructorWithCoursesJoinFetch(AppDao app){
    int id = 10;
    System.out.println("Find instructor id:" + id);

    Instructor ins = app.findInstructorByIdJoinFetch(id);

    if (ins == null) {
      System.out.println("No instructor found with id: " + id);
      return;
    }

    System.out.println("Instructor: " + ins);
    System.out.println("Courses: " + ins.getCourses());
  }


  private void updateInstructor(AppDao app){
    int id = 6;
    System.out.println("Find instructor id: " + id);

    Instructor ins = app.findInstructorById(id);

    ins.setLastName("Hatdog");

    System.out.println("Updated!");
    app.update(ins);
  }


  private void deleteInstructorOnly(AppDao app){
    int id = 10;
    System.out.println("Delete Instrutor: " + id);
    app.deleteInstructorById(id);
    System.out.println("Deleted Successfully!");
  }


  private void createCourseAndReview(AppDao app){
    Course course = new Course("How to Speed Run Spring in 1 Day");

    course.addReview(new Review("Great speed 2x ....... loved it!!!"));
    course.addReview(new Review("I wanted to speed 0.75x :((("));
    course.addReview(new Review("Hoping to do  speed of 3x :D. Anyways, nice!"));

    System.out.println("Course Saved!");
    app.save(course);
  }


  private void retrieveCourseAndReviews(AppDao app){
    int id = 6;
    System.out.println("Get course and review from id:" + id);

    Course course = app.findCourseAndReviewByCourseId(id);

    System.out.println(course);
    System.out.println(course.getReview());
  }


  private void deleteCourseAndreviews(AppDao app){
    int id = 6;
    System.out.println("Delete course:" + id);
    app.deleteCourseById(id);
    System.out.println("Deleted Successfully!");
  }


  private void createCourseAndStudents(AppDao app){
    Course course = new Course("How To Be A FullStack Dev in Half A DAy");

    Student student1 = new Student("Light", "Yagami", "light.yagami@example.com");
    Student student2 = new Student("Lesie", "Andrews", "leslie.andrews@example.com");

    course.addStudent(student1);
    course.addStudent(student2);

    app.save(course);
    System.out.println("Successully enrolled!");
  }


  private void findCourseAndStudentByCourseId (AppDao app){
    int id = 8;
    Course course = app.findCourseAndStudentByCourseId(id);
    System.out.println("Course: " + course);
    System.out.println("Students: " + course.getStudents());
  }


private void findStudentAndCourse(AppDao app){
  int id = 21;
  Student student = app.findStudentAndCoursesByStudentId(id);

  System.out.println("Student: " + student);
  System.out.println("Courses: " + student.getCourses());
}


private void addMoreCoursesForStudent(AppDao app){
  int id = 22;

  Student student = app.findStudentAndCoursesByStudentId(id);
  if (student == null) {
    System.out.println("No student found with id: " + id);
    return;
  }

  Course course1 = new Course ("How to Test without Running Application");
  Course course2 = new Course ("How to Develop without Computer");
  Course course3 = new Course ("How to Be a Legit Developer");

  student.addCourse(course1);
  student.addCourse(course2);
  student.addCourse(course3);

  System.out.println("Succesfully enrolled to new courses!");
  app.update(student);
}


private void deleteCourseById(AppDao app){
  int id = 23;
System.out.println("Delete course: " + id);
app.deleteCourseById(id);
System.out.println("Deleted successfully!");
}


private void deleteStudent(AppDao app){
  int id = 22;
  System.out.println("Delete student: " + id);

  app.deleteCourseById(id);
  System.out.println("Deleted successfully!");
}


}
