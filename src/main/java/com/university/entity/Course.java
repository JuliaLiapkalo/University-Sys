package com.university.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseName;
    private String startDate; //LocalDate dos(dayOfStart) LocalDate.of(2000, Month.JULY, 6)
    private String endDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> students;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private Professor professor;

    public Course(){

    }
    public Course(String courseName, String startDate, String endDate, List<Student> students, Professor professor) {
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.students = students;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Course{" +
//                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", students=" + students.toString() +
                ", professor=" + professor.getName() +
                '}';
    }
}
