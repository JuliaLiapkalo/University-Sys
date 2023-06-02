package com.university.service;

import com.university.entity.Professor;
import com.university.repository.ProfessorRepository;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    @Transactional
    public Long create(Professor professor){
       return professorRepository.save(professor).getId();
    }
    public List<Professor> findAllProfessors(){
        return professorRepository.findAll();
    }
    @Transactional
    public void delete(Long id){
        professorRepository.deleteProfessorByIdInCourse(id);
        professorRepository.deleteProfessorById(id);
    }
    @Transactional
    public void updateProfessor(Long id, String name) {
        Professor updatedProfessor = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("professor with id: " + id + "does not exist"));
        updatedProfessor.setName(name);

    }
//    @Transactional
//    public void setProfessorToCourse(Long professorId, Long courseId){
//        professorRepository.setProfessor(professorId, courseId);
//    }

    public Professor findById(Long id){
       return professorRepository.findById(id).get();
    }

}
