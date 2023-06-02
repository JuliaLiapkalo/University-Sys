package com.university.repository;

import com.university.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE course SET professor_id = NULL where professor_id=?")
    void deleteProfessorByIdInCourse(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "delete from professor where id=?1")
    void deleteProfessorById(Long id);

//    @Modifying
//    @Query(nativeQuery = true, value = "UPDATE course set professor_id=?1 where id=?2")
//    void setProfessor(Long professorId, Long courseId);
}
