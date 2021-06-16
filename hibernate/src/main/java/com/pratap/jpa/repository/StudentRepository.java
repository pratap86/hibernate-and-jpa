package com.pratap.jpa.repository;

import com.pratap.jpa.entity.Course;
import com.pratap.jpa.entity.Passport;
import com.pratap.jpa.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Slf4j
public class StudentRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Student saveStudentWithPassport(Student student){

        entityManager.persist(student.getPassport());
        entityManager.persist(student);
        return student;
    }

    @Transactional
    public Student saveStudentAndCourse(Student student, Course course){

        log.info("Going to execute saveStudentAndCourse() by persisting Student = {} and Course = {}", student, course);
        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
        return student;
    }
}
