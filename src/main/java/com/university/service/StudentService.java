package com.university.service;

import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteStudentByIdInCourse(id);
        studentRepository.deleteStudentById(id);
    }
}
