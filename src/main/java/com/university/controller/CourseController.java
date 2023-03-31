package com.university.controller;

import com.university.entity.Course;
import com.university.entity.Professor;
import com.university.entity.Student;
import com.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/courses")
@RestController
public class CourseController {

    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/new")
    public String create(@RequestBody Course course){
        courseService.save(course.getCourseName(), course.getStartDate(), course.getEndDate(),
                course.getStudents(),
//                        .stream()
//                .map(studentRequestDTO -> new Student(studentRequestDTO.getName())).collect(Collectors.toList()),
                course.getProfessor());
        return "courses/new";
    }
    @GetMapping()
    public List<String> findAll(){
        return courseService.findAll().stream().map(Course::getCourseName).collect(Collectors.toList());
    }
    @GetMapping("/name/{name}")
    public String findByName(@PathVariable String name){
        return courseService.findByName(name).toString();
    }

    @GetMapping("/id/{id}")
    public String findById(@PathVariable Long id){
        return courseService.findById(id).toString();
    }

    @PatchMapping ("/edit/{id}")
    public String edit(@PathVariable Long id, @RequestParam(required = false) String courseName,
                       @RequestParam(required = false)String startDate,
                       @RequestParam(required = false) String endDate,
                       @RequestParam(required = false)List<Student> students,
                       @RequestParam(required = false) Professor professor){
        courseService.update(id, courseName, startDate, endDate, students, professor);
        return "Successes";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        courseService.delete(id);
        return "Successes";
    }
}