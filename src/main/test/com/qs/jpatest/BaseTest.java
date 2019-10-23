package com.qs.jpatest;

import com.qs.jpatest.dao.StudentDao;
import com.qs.jpatest.domain.Student;
import com.qs.jpatest.service.StudentService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private StudentDao studentDao;

    @Resource
    private StudentService studentService;

    @Test
    public void test() {
        System.out.println("===>>" + dataSource);
        System.out.println("===>>" + entityManagerFactory);
        System.out.println("===>>" + studentDao);
    }

    @Test
    public void test2() {
        Student student = studentDao.findOne("1");
        System.out.println("==>>student=" + student);
    }

    @Test
    public void test3() {
        System.out.println("===>>" + studentService.getAllStudentIds());
        System.out.println("===>>" + studentService.getAllStudentIds());
    }

    /**
     * 测试ehcache缓存使用：cache.put(element)  cache.getKey().getObjectValue()
     */
    @Test
    public void test4() {
        studentService.getStudentMap();
        Cache cache = CacheManager.getInstance().getCache("com.qs.jpatest.service.impl.StudentServiceImpl.getStudentMap");
        List keys = cache.getKeys();
        List<Student> students = new Stack<Student>();
        for (Object key : keys) {
            Object objectValue = cache.get(key).getObjectValue();
            if (objectValue instanceof Student) {
                students.add((Student) objectValue);
            }
        }

        System.out.println("===>>students=" + students);
    }
}
