package com.pratap.jpa.repository;

import com.pratap.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PerformanceTuningTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceTuningTest.class);

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void testCreateNPlusOneProblem(){

        List<Course> courses = entityManager.createNamedQuery("get_all_course_query", Course.class).getResultList();
        courses.forEach(course -> LOGGER.info("Course={} and Student={}", course, course.getStudents()));
    }

    @Test
    @Transactional
    void testSolvingNPlusOneProblem_By_EntityGraph(){

        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");
        List<Course> courses = entityManager.createNamedQuery("get_all_course_query", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        courses.forEach(course -> LOGGER.info("Course={} and Student={}", course, course.getStudents()));
    }

    @Test
    @Transactional
    void testSolvingNPlusOneProblem_By_Join_Fetch(){

        List<Course> courses = entityManager.createNamedQuery("get_all_course_join_fetch_query", Course.class).getResultList();
        courses.forEach(course -> LOGGER.info("Course={} and Student={}", course, course.getStudents()));
    }
}
