package com.university.service;
import com.university.entity.Student;
import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).get();
    }
    public List<Student> findAllStudent(){
        return studentRepository.findAll();
    }

    public Long saveStudent(Student student){
        return studentRepository.save(student).getId();
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteStudentByIdInCourse(id);
        studentRepository.deleteById(id);
    }

//    @Transactional
//    public void deleteAllStudents(){
//        studentRepository.deleteAllStudents();
//    }

    @Transactional
    public void update(Long id, String studentName, String studentLastName){
        Student updatedStudent = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("student with id: " + id + "does not exist"));
        updatedStudent.setName(studentName);
        updatedStudent.setLastName(studentLastName);

    }
//    @Transactional
//    public void addCourseToStudent(Long studentId, Long courseId) {
//        studentRepository.setCourseForStudent(studentId, courseId);
//    }
}
