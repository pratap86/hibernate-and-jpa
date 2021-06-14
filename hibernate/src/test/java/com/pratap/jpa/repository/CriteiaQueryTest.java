package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Course;
import com.pratap.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = HibernateApplication.class)
@ExtendWith(SpringExtension.class)
public class CriteiaQueryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void testGetAllCourses_Basic(){

        // JPQL : select c from Course c : build this query through java by below 4 steps
        // 1. use Criteria Builder to create a Criteria Query returning the
        // 1.1 expected result Object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        // 2. Defines root for tables which are involved in the query ie. 'from Course'
        Root<Course> courseRoot = criteriaQuery.from(Course.class);
        // 3. Defines Predicates etc using Criteria Builder
        // 4. Add Predicate etc to the Criteria Query
        // 5. Build the TypedQuery using the EntityManager and Criteria Query
        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();

        Iterable<String> courses = Arrays.asList("Hibernate in 100 steps", "Spring in 100 steps", "Microservices", "Docker", "Kubernetes");
        assertThat(resultList)
                .isNotEmpty()
                .hasSize(5)
                .extracting(Course::getName)
                .containsExactlyElementsOf(courses);
    }

    @Test
    void testGetAllCourses_having_100_steps(){

        // JPQL : " select c from Course c where c.name like '%100 steps%' "
        // 1. use Criteria Builder to create a Criteria Query returning the
        // 1.1 expected result Object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        // 2. Defines root for tables which are involved in the query ie. 'from Course'
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        // 3. Defines Predicates etc using Criteria Builder
        Predicate like100Steps = criteriaBuilder.like(courseRoot.get("name"), "%100 steps");
        // 4. Add Predicate etc to the Criteria Query
        criteriaQuery.where(like100Steps);

        // 5. Build the TypedQuery using the EntityManager and Criteria Query
        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();

        Iterable<String> courses = Arrays.asList("Hibernate in 100 steps", "Spring in 100 steps");
        assertThat(resultList)
                .isNotEmpty()
                .hasSize(2)
                .extracting(Course::getName)
                .containsExactlyElementsOf(courses);
    }

    @Test
    void testGetAllCourses_having_no_students(){

        // JPQL : " select c from Course c where c.students is empty "
        // 1. use Criteria Builder to create a Criteria Query returning the
        // 1.1 expected result Object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        // 2. Defines root for tables which are involved in the query ie. 'from Course'
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        // 3. Defines Predicates etc using Criteria Builder
        Predicate isEmptyStudents = criteriaBuilder.isEmpty(courseRoot.get("students"));
        // 4. Add Predicate etc to the Criteria Query
        criteriaQuery.where(isEmptyStudents);

        // 5. Build the TypedQuery using the EntityManager and Criteria Query
        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();

        Iterable<String> courses = Arrays.asList("Hibernate in 100 steps", "Docker", "Kubernetes");
        assertThat(resultList)
                .isNotEmpty()
                .hasSize(3)
                .extracting(Course::getName)
                .containsExactlyElementsOf(courses);
    }

    @Test
    @Transactional
    void testGetCourses_and_students_with_inner_join(){

        // JPQL : " select c from Course c join c.students s "
        // 1. use Criteria Builder to create a Criteria Query returning the
        // 1.1 expected result Object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        // 2. Defines root for tables which are involved in the query ie. 'from Course'
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        // 3. Defines Predicates etc using Criteria Builder
        courseRoot.join("students");
        // 4. Add Predicate etc to the Criteria Query

        // 5. Build the TypedQuery using the EntityManager and Criteria Query
        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();

        Iterable<String> courses = Arrays.asList("Spring in 100 steps", "Spring in 100 steps", "Spring in 100 steps", "Microservices");
        assertThat(resultList)
                .isNotEmpty()
                .hasSize(4)
                .extracting(Course::getName)
                .containsExactlyElementsOf(courses);

        assertThat(resultList)
                .isNotEmpty()
                .hasSize(4)
                .extracting(Course::getStudents)
                .hasSize(4);
    }

    @Test
    void testGetCourses_and_students_with_left_join(){

        // JPQL : " select c from Course c join c.students s "
        // 1. use Criteria Builder to create a Criteria Query returning the
        // 1.1 expected result Object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        // 2. Defines root for tables which are involved in the query ie. 'from Course'
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        // 3. Defines Predicates etc using Criteria Builder
        courseRoot.join("students", JoinType.LEFT);
        // 4. Add Predicate etc to the Criteria Query

        // 5. Build the TypedQuery using the EntityManager and Criteria Query
        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();

        Iterable<String> courses = Arrays.asList("Spring in 100 steps", "Spring in 100 steps", "Spring in 100 steps", "Microservices");

        assertThat(resultList)
                .isNotEmpty()
                .hasSize(7)
                .extracting(Course::getName)
                .contains("Spring in 100 steps", "Spring in 100 steps", "Spring in 100 steps");
    }
}
