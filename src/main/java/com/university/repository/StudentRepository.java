package com.university.repository;

import com.university.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "delete from student where id=?1")
    void deleteStudentById(Long studentId);
    @Modifying
    @Query(nativeQuery = true, value = "delete from course_students where students_id=?1")
    void deleteStudentByIdInCourse(Long studentId);

    @Modifying
    @Query(nativeQuery = true, value = "TRUNCATE TABLE student")
    void deleteAllStudents();

    @Modifying
    @Query(nativeQuery = true, value = "DELETE ")
    void deleteStudentFromCourse(Long courseId);
}