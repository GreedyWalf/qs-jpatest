package com.qs.jpatest.service.impl;

import com.qs.jpatest.dao.StudentDao;
import com.qs.jpatest.domain.Student;
import com.qs.jpatest.service.StudentService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public List<String> getAllStudentIds() {
        return studentDao.findAllStudentIds();
    }


    @Override
    public Map<String, Student> getStudentMap() {
        Cache cache = CacheManager.getInstance().getCache("com.qs.jpatest.service.impl.StudentServiceImpl.getStudentMap");
        Map<String, Student> resultMap = new HashMap<String, Student>();
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            resultMap.put(student.getStudentId(), student);
            cache.put(new Element(student.getStudentId(), student));
        }

        return resultMap;
    }
}
