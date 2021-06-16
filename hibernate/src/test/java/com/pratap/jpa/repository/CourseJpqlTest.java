package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Course;
import com.pratap.jpa.entity.Passport;
import com.pratap.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HibernateApplication.class)
public class CourseJpqlTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void testSelectJpql_basic(){

        Query query = entityManager.createQuery("Select c from Course c");
        List resultList = query.getResultList();
        assertEquals(5, resultList.size());

    }

    @Test
    void testSelectJpql_typed(){

        TypedQuery<Course> typedQuery = entityManager.createQuery("Select c from Course c", Course.class);
        List<Course> courses = typedQuery.getResultList();
        assertEquals(5, courses.size());

    }

    @Test
    void testSelectJpql_where_clause(){

        TypedQuery<Course> typedQuery = entityManager.createQuery("Select c from Course c where c.name like '%100 steps'", Course.class);
        List<Course> courses = typedQuery.getResultList();
        assertEquals(2, courses.size());

    }

    @Test
    void testNamedQuery_selectAll(){

        TypedQuery<Course> namedQuery = entityManager.createNamedQuery("get_all_course_query", Course.class);
        List<Course> courses = namedQuery.getResultList();
        assertEquals(5, courses.size());
    }

    @Test
    void testNamedQuery_where_clause(){

        TypedQuery<Course> namedQuery = entityManager.createNamedQuery("get_100_steps_query", Course.class);
        List<Course> courses = namedQuery.getResultList();
        assertEquals(2, courses.size());
    }

    @Test
    void testJPQL_courses_without_students(){
        List<Course> courses = entityManager.createQuery("select c from Course c where c.students is empty", Course.class).getResultList();

        List<String> expectedCourses = Arrays.asList("Hibernate in 100 steps", "Docker", "Kubernetes");
        assertThat(courses)
                .isNotEmpty()
                .hasSize(3)
                .doesNotHaveDuplicates()
                .extracting(Course::getName)
                .containsAll(expectedCourses);
    }

    @Test
    void testJPQL_course_with_min_two_students(){

        List<Course> courses = entityManager.createQuery("select c from Course c where size(c.students) >= 2", Course.class).getResultList();

        assertThat(courses)
                .isNotEmpty()
                .hasSize(1)
                .extracting(Course::getName)
                .isEqualTo(Collections.singletonList("Spring in 100 steps"));

    }

    @Test
    void testJPQL_courses_ordered_by_students(){

        List<Course> courses = entityManager.createQuery("select c from Course c order by size(c.students)", Course.class).getResultList();

        List<String> expected = Arrays.asList("Hibernate in 100 steps", "Docker", "Kubernetes", "Microservices", "Spring in 100 steps");
        assertThat(courses)
                .isNotEmpty()
                .hasSize(5)
                .extracting(Course::getName)
                .containsExactlyElementsOf(expected);
    }

    @Test
    @Transactional
    void testJPQL_students_with_passport_in_a_certain_pattern(){

        List<Student> students = entityManager.createQuery("select s from Student s where s.passport.passportNumber like '%1234%'", Student.class).getResultList();

        List<String> expected = Arrays.asList("E123456", "N123457");
        assertThat(students)
                .isNotEmpty()
                .hasSize(2)
                .extracting(Student::getPassport)
                .extracting(Passport::getPassportNumber)
                .containsExactlyElementsOf(expected);
    }

    @Test
    void testJPQLJoin(){
        Query query = entityManager.createQuery("select c, s from Course c join c.students s");
        List resultList = query.getResultList();
        assertThat(resultList)
                .isNotEmpty()
                .hasSize(4);

    }

    @Test
    void testJPQLLeftJoin(){
        Query query = entityManager.createQuery("select c, s from Course c left join c.students s");
        List resultList = query.getResultList();
        assertThat(resultList)
                .isNotEmpty()
                .hasSize(7);

    }

    @Test
    void testJPQLCrossJoin(){
        Query query = entityManager.createQuery("select c, s from Course c, Student s");
        List resultList = query.getResultList();
        assertThat(resultList)
                .isNotEmpty()
                .hasSize(15);

    }
}
