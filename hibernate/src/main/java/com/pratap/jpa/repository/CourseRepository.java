package com.pratap.jpa.repository;

import com.pratap.jpa.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Slf4j
public class CourseRepository {

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id){

        log.info("Executing findById(), id = {}", id);
        return entityManager.find(Course.class, id);
    }

    @Transactional
    public void deleteById(Long id){

        log.info("Executing deleteById, id = {}", id);
        Course course = findById(id);

        log.info("Going to delete course = {}", course);
        entityManager.remove(course);
    }

    @Transactional
    public Course save(Course course){

        log.info("Executing save() with course = {}", course);
        if (course.getId() == null) {
            log.info("Going to persist new Record, course = {}", course);
            entityManager.persist(course);
        }
        else{
            log.info("Going to merge existing Record, course = {}", course);
            entityManager.merge(course);
        }
        return course;
    }
}
