package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HibernateApplication.class)
public class CourseNativeQueryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void testNativeQuery_Basic(){

        Query query = entityManager.createNativeQuery("Select * from course", Course.class);
        List resultList = query.getResultList();
        assertEquals(5, resultList.size());
    }

    @Test
    void testNativeQuery_With_Where_Clause(){

        Query query = entityManager.createNativeQuery("Select * from course c where c.id = ?", Course.class);
        query.setParameter(1, 10001L);
        List courses = query.getResultList();
        assertEquals(1, courses.size());
    }

    @Test
    @Transactional
    void testNativeQuery_Use_For_Batch_Update(){

        Query query = entityManager.createNativeQuery("update course set last_updated_date = sysdate()", Course.class);
        int numberOfRowsUpdate = query.executeUpdate();
        assertEquals(5, numberOfRowsUpdate);

    }
}
