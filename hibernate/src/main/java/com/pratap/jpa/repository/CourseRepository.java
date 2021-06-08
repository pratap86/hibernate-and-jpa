package com.pratap.jpa.repository;

import com.pratap.jpa.entity.Course;
import com.pratap.jpa.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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
        // Review is Owning side the relationship, ie it should be delete first
        // Spring DataIntegrityViolationException
        List<Review> reviews = course.getReviews();
        reviews.forEach(review -> entityManager.remove(review));

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

    @Transactional
    public Course addReviewsForCourse(Long courseId, List<Review> reviews){

        log.info("Executing addReviewsForCourse(), with courseId = {}, and reviews = {}", courseId, reviews);
        Course course = entityManager.find(Course.class, courseId);
        reviews.forEach(review -> {
            // setting the relationship
            course.addReview(review);
            review.setCourse(course);
            // persist in to data base
            entityManager.persist(review);
        });
        return course;
    }
}
