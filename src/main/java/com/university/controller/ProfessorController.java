package com.university.controller;

import com.university.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;
    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProfessor(@PathVariable Long id){
        professorService.delete(id);
    }
}

