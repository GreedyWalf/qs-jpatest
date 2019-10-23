package com.qs.jpatest.service;

import com.qs.jpatest.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<String> getAllStudentIds();

    Map<String, Student> getStudentMap();
}
