package com.university.repository;
import com.university.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM course WHERE course_name = ?1")
    List<Course> findByName(String courseName);

}
