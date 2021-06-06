package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HibernateApplication.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Test
    void testFindById_Basic() {
        Course course = repository.findById(10001L);
        assertEquals("Hibernate in 100 steps", course.getName());
    }

    @Test
    @DirtiesContext//reset the data for further use
    void testDeleteById_Basic(){
        repository.deleteById(10001L);
        assertNull(repository.findById(10001L));
    }

    @Test
    void testSave_New_Record(){
        Course course = new Course("AWS");
        Course newCourse = repository.save(course);
        assertNotNull(newCourse);
        assertEquals("AWS", newCourse.getName());
    }

    @Test
    @DirtiesContext
    void testSave_Existing_Record(){
        Course course = repository.findById(10001L);
        course.setName("AngularJS");
        Course updatedCourse = repository.save(course);
        assertNotNull(updatedCourse);
        assertEquals("AngularJS", updatedCourse.getName());
    }

}