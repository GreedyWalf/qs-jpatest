package com.qs.jpatest.controller;

import com.qs.jpatest.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public String test(Model model, HttpServletRequest request) {

        Student student = new Student();
        student.setStudentName("张三");
        model.addAttribute("student", student);

        List<Student> studentList = new ArrayList<Student>();
        Student student2 = new Student();
        student2.setStudentId("1");
        student2.setStudentName("李四");
        studentList.add(student2);
        Student student3 = new Student();
        student3.setStudentId("2");
        student3.setStudentName("王五");
        studentList.add(student3);
        model.addAttribute("studentList", studentList);

        Map<String, Student> studentNameAndStudentMap = new HashMap<String, Student>();
        studentNameAndStudentMap.put("张三", student);
        studentNameAndStudentMap.put("李四", student2);
        studentNameAndStudentMap.put("王五", student3);
        model.addAttribute("studentMap", studentNameAndStudentMap);

        return "test";
    }
}
