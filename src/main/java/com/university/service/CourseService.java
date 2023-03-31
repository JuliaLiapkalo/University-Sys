package com.university.service;

import com.university.entity.Course;
import com.university.entity.Professor;
import com.university.entity.Student;
import com.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course findById(Long id){
        Optional<Course> foundCourse = courseRepository.findById(id);
        return foundCourse.orElse(null);
    }
    public List<Course> findByName(String name){
        return courseRepository.findByName(name);
    }


    @Transactional
    public Long save(String courseName, String startDate, String endDate, List<Student> students, Professor professor){
        return courseRepository.save(new Course
                (courseName, startDate, endDate, students, professor)).getId();
    }
    public List<Course> findAll(){
        return courseRepository.findAll();
    }
    @Transactional
    public void update(Long id, String courseName, String startDate, String endDate, List<Student> students, Professor professor){
        Course updatedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("course with id: " + id + "does not exist"));
        updatedCourse.setCourseName(courseName);
        updatedCourse.setStartDate(startDate);
        updatedCourse.setEndDate(endDate);
        updatedCourse.setStudents(students);
        updatedCourse.setProfessor(professor);
    }
    @Transactional
    public void delete(Long id){
        courseRepository.deleteById(id);
    }

}