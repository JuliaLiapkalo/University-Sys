package com.university.controller;

import com.university.entity.Course;
import com.university.entity.Professor;
import com.university.service.CourseService;
import com.university.service.ProfessorService;
import com.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequestMapping("/courses")
@Controller
public class CourseController {

    private final CourseService courseService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/new")
    public String newCourse(Model model){
        model.addAttribute("course", new Course());
        return "course/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("course") Course course){
        this.courseService.save(course);
        professorService.delete(courseService.getProfessorOfCourse(course.getId()).getId());
        return "redirect:/courses";
    }
    @GetMapping()
    String findAll(Model model){
        model.addAttribute("courses",
//                courseService.findAll());
                courseService.findAll().stream()
                        .map(c -> new Course(c.getId(), c.getCourseName(), c.getStartDate(), c.getEndDate(), c.getStudents(), c.getProfessor()))
                        .collect(Collectors.toList()));
        return "course/findAll";
    }

//    @GetMapping("/name/{name}")
//    public String findByName(@PathVariable String name, Model model){
//        model.addAttribute("findByName", courseService.findByName(name).toString());
//        return "course/findByName";
//    }

//    @GetMapping("/id/{id}")
//    public String findById(@PathVariable Long id, Model model){
//        model.addAttribute("findById", courseService.findById(id).toString());
//        return "course/findById";
//    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable("id") Long id){
        model.addAttribute("course", courseService.findById(id));
        return "course/updateCourse";
    }
    @PatchMapping ("/edit/{id}")
    public String edit(@ModelAttribute("course") Course course, @PathVariable Long id){
        courseService.update(id, course.getCourseName(), course.getStartDate(), course.getEndDate());
        return "redirect:/courses";
    }

    @GetMapping ("/delete/{id}")
    public String delete(@PathVariable Long id){
        courseService.delete(id);
        return "redirect:/courses";
    }

    @GetMapping("/set-professor/{id}")
    public String setProfessorToCourse(Model model, @PathVariable("id") Long courseId) {
        model.addAttribute("course", courseService.findById(courseId));
        model.addAttribute("professors", professorService.findAllProfessors());
        return "course/professorForCourse";
    }

    @PatchMapping("/set-professor/{id}/{pId}")
    public String setProfessor(@ModelAttribute("course") Course course,
                               @PathVariable("pId") Long professorId) {
        courseService.setProfessorToCourse(professorId, course.getId());
        return "redirect:/courses";
    }

    @GetMapping("/get-professor/{id}")
    public String getProfessorOfCourse(Model model, @PathVariable Long id) {
        model.addAttribute("professor", courseService.getProfessorOfCourse(id));
        model.addAttribute("course", courseService.findById(id));
        return "course/professorOfCourse";
    }

    @GetMapping("/set-student/{id}")
    public String setStudentToCourse(Model model, @PathVariable("id") Long courseId) {
        model.addAttribute("courseForStudent", courseService.findById(courseId));
        model.addAttribute("allStudents", studentService.findAllStudent());
        model.addAttribute("students", courseService.findById(courseId).getStudents());
        model.addAttribute("studentNotFromCourse", courseService.getStudentsNotFromCourse(courseId));
        return "course/studentForCourse";
    }
    @PatchMapping ("/set-student/{id}/{sId}")
    public String setStudent(@ModelAttribute("courseForStudent") Course course,
                             @PathVariable("sId") Long studentId) {
        courseService.setStudentForCourse(studentId, course.getId());
        return "redirect:/courses";
    }
    @GetMapping("/get-student/{id}")
    public String getStudentOfCourse(Model model, @PathVariable Long id) {
        model.addAttribute("students", courseService.getStudentOfCourse(id));
        model.addAttribute("course", courseService.findById(id));
        return "course/studentOfCourse";
    }

    @GetMapping("/delete-professor/{cId}/{pId}")
    public String deleteProfessorFromCourse(@PathVariable Long cId, @PathVariable Long pId) {
        courseService.deleteProfessorFromCourse(cId, pId);
        return "redirect:/courses";
    }

    @GetMapping("/delete-student/{sId}")
    public String deleteStudentFromCourse(@PathVariable Long sId) {
        courseService.deleteStudentFromCourse(sId);
        return "redirect:/courses";
    }
}