package com.university.service;

import com.university.entity.Course;
import com.university.entity.Professor;
import com.university.entity.Student;
import com.university.repository.CourseRepository;
import com.university.repository.ProfessorRepository;
import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Course findById(Long id){
        Optional<Course> foundCourse = courseRepository.findById(id);
        return foundCourse.orElse(null);
    }
//    public Course findByName(String name){
//        return courseRepository.findCourseByCourseName(name);
//    }


    @Transactional
    public Long save(Course course){
        return courseRepository.save(course).getId();
    }
    public List<Course> findAll(){
        return courseRepository.findAll();
    }
    @Transactional
    public void update(Long id, String courseName, String startDate, String endDate){
        Course updatedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("course with id: " + id + "does not exist"));
        updatedCourse.setCourseName(courseName);
        updatedCourse.setStartDate(startDate);
        updatedCourse.setEndDate(endDate);
//        updatedCourse.setStudents(students);
//        updatedCourse.setProfessor(professor);
    }
    @Transactional
    public void delete(Long id){
        courseRepository.deleteById(id);
    }

    @Transactional
    public void setProfessorToCourse(Long professorId, Long courseId) {
        courseRepository.setProfessorForCourse(professorId, courseId);
        courseRepository.setCourseForProfessor(professorId, courseId);
    }

    public Professor getProfessorOfCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        return course.getProfessor();
    }

    public List<Student> getStudentOfCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        return course.getStudents();
    }

    public List<Student> getStudentsNotFromCourse (Long courseId){
        List<Student> studentListOfCourse = courseRepository.getOne(courseId).getStudents();
        List<Student> listAllStudent = studentRepository.findAll();
        listAllStudent.removeAll(studentListOfCourse);
        return listAllStudent;
    }
    @Transactional
    public void deleteProfessorFromCourse(Long cId, Long pId) {
        courseRepository.deleteCourseForProfessor(pId);
        courseRepository.deleteProfessorForCourse(cId);
    }

    @Transactional
    public void setStudentForCourse(Long cId, Long sId) {
        entityManager.createNativeQuery("INSERT INTO course_students (course_id, students_id) VALUES (?,?)")
                .setParameter(1, sId)
                .setParameter(2, cId)
                .executeUpdate();
    }
    @Transactional
    public void deleteStudentFromCourse(Long sId) {
        courseRepository.deleteStudentFromCourse(sId);
    }
}