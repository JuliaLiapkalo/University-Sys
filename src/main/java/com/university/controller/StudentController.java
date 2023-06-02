package com.university.controller;

import com.university.entity.Student;
import com.university.service.CourseService;
import com.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private  StudentService studentService;
    private CourseService courseService;

    @GetMapping
    public String findAllStudents(Model model) {
        model.addAttribute("students", studentService.findAllStudent());
        return "student/findAllStudents";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/new";
    }

    @PostMapping
    public String createStudent(@ModelAttribute("student") Student student) {
        this.studentService.saveStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

//    @GetMapping("/deleteAll")
//    public String deleteAllStudents() {
//        studentService.deleteAllStudents();
//        return "redirect:/students";
//    }

    @GetMapping("/edit/{id}")
    public String editStudent(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student/updateStudent";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("student") Student student, @PathVariable("id") Long id) {
        studentService.update(id, student.getName(), student.getLastName());
        return "redirect:/students";
    }
//    @GetMapping("/set-courses/{id}")
//    public String setCourse(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("student", studentService.getStudentById(id));
//        model.addAttribute("courses", courseService.findAll());
//        return "student/addCourseForStudent";
//    }
//    @PatchMapping("/set-courses/{id}")
//    public String addCourseForStudent(@PathVariable("id") Long studentId, Long courseId) {
//        studentService.addCourseToStudent(studentId, courseId);
//        return "redirect:/students";
//    }
}