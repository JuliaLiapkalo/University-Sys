package com.university.repository;
import com.university.entity.Course;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

      Course findCourseByCourseName(String name);

      @Modifying
      @Query(nativeQuery = true, value = "UPDATE course set professor_id=?1 where id=?2")
      void setProfessorForCourse(Long professorId, Long courseId);

      @Modifying
      @Query(nativeQuery = true, value = "UPDATE professor set course_id=?2 where id=?1")
      void setCourseForProfessor(Long professorId, Long courseId);


      @Modifying
      @Query(nativeQuery = true, value = "update course set professor_id = null where id=?")
      void deleteProfessorForCourse(Long courseId);

      @Modifying
      @Query(nativeQuery = true, value = "UPDATE professor set course_id = NULL where id=?")
      void deleteCourseForProfessor(Long professorId);

      @Modifying
      @Query(nativeQuery = true, value = "DELETE FROM course_students where students_id=?")
      void deleteStudentFromCourse(Long sId);

}
