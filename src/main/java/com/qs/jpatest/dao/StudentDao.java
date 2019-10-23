package com.qs.jpatest.dao;

import com.qs.jpatest.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, String> {

    @Query(value = "select student_id from t_student", nativeQuery = true)
    List<String> findAllStudentIds();

}
