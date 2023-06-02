package com.university.controller;

import com.university.entity.Professor;
import com.university.service.CourseService;
import com.university.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;
    private CourseService courseService;
    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/new")
    public String createProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/new";
    }
    @PostMapping()
    public String newProfessor(@ModelAttribute("professor") Professor professor) {
        professorService.create(professor);
        return "redirect:/professors";
    }
    @GetMapping()
    public String showAllProfessors(Model model) {
        model.addAttribute("professors", professorService.findAllProfessors());
        return "professor/allProfessors";
    }
    @GetMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorService.delete(id);
        return "redirect:/professors";
    }
    @GetMapping("/edit/{id}")
    public String editProfessor(Model model, @PathVariable("id") Long id) {
        model.addAttribute("professor", professorService.findById(id));
        return "professor/updateProfessor";
    }
    @PatchMapping("/edit/{id}")
    public String updateProfessor(@ModelAttribute("professor") Professor professor, @PathVariable("id") Long id) {
        professorService.updateProfessor(id, professor.getName());
        return "redirect:/professors";
    }

//    @GetMapping("/set-professor/{cId}")
//    public String setProfessorToCourse(Model model, @PathVariable("cId") Long courseId, Long professorId) {
//        model.addAttribute("course", courseService.findById(courseId));
//        model.addAttribute("professor", professorService.findById(professorId));
//        return "professor/setProfessorToCourse";
//    }
//    @PatchMapping("/set-professor/{courseId}")
//    public String setProfessor(@ModelAttribute("professor") Professor professor, @PathVariable("courseId") Long courseId, Long professorId) {
//        professorService.setProfessorToCourse(professorId, courseId);
//            return "redirect:/courses";
//    }
}

