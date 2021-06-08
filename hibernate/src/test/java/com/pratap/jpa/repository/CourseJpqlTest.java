package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HibernateApplication.class)
public class CourseJpqlTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void testSelectJpql_Basic(){

        Query query = entityManager.createQuery("Select c from Course c");
        List resultList = query.getResultList();
        assertEquals(5, resultList.size());

    }

    @Test
    void testSelectJpql_Typed(){

        TypedQuery<Course> typedQuery = entityManager.createQuery("Select c from Course c", Course.class);
        List<Course> courses = typedQuery.getResultList();
        assertEquals(5, courses.size());

    }

    @Test
    void testSelectJpql_Where_Clause(){

        TypedQuery<Course> typedQuery = entityManager.createQuery("Select c from Course c where c.name like '%100 steps'", Course.class);
        List<Course> courses = typedQuery.getResultList();
        assertEquals(2, courses.size());

    }

    @Test
    void testNamedQuery_SelectAll(){

        TypedQuery<Course> namedQuery = entityManager.createNamedQuery("get_all_course_query", Course.class);
        List<Course> courses = namedQuery.getResultList();
        assertEquals(5, courses.size());
    }

    @Test
    void testNamedQuery_Where_Clause(){

        TypedQuery<Course> namedQuery = entityManager.createNamedQuery("get_100_steps_query", Course.class);
        List<Course> courses = namedQuery.getResultList();
        assertEquals(2, courses.size());
    }
}
