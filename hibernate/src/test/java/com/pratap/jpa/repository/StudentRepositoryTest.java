package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Address;
import com.pratap.jpa.entity.Course;
import com.pratap.jpa.entity.Passport;
import com.pratap.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HibernateApplication.class)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    @DirtiesContext
    void testSaveStudentWithPassport(){

        Passport passport = new Passport("M1234569");
        Student student = new Student("Krishna");
        student.setAddress(new Address(111, "Neta Ji Road", "Gurugram"));
        student.setPassport(passport);
        Student savedStudent = studentRepository.saveStudentWithPassport(student);
        assertNotNull(savedStudent);
        assertEquals("Krishna", savedStudent.getName());
        assertEquals("M1234569", savedStudent.getPassport().getPassportNumber());
    }

    @Test
    @Transactional
    void testGetStudentAndPassportDetails(){

        Student student = entityManager.find(Student.class, 20001L);
        assertNotNull(student);
        assertEquals("Sankalp", student.getName());
        assertEquals("E123456", student.getPassport().getPassportNumber());
    }

    @Test
    void testGetStudentFromPassport_Bi_Directional(){

        Passport passport = entityManager.find(Passport.class, 40001L);
        assertNotNull(passport);
        assertEquals("E123456", passport.getPassportNumber());
        assertEquals("Sankalp", passport.getStudent().getName());
    }

    @Test
    @Transactional
    void testGetStudentAndCourses(){

        Student student = entityManager.find(Student.class, 20001L);

        assertEquals("Sankalp", student.getName());
        assertEquals(2, student.getCourses().size());
    }

    @Test
    @Transactional
    void testGetCourseAndStudents(){

        Course course = entityManager.find(Course.class, 10002L);

        assertEquals("Spring in 100 steps", course.getName());
        assertEquals(3, course.getStudents().size());
    }

    @Test
    @Transactional
    @DirtiesContext
    void testSaveStudentAndCourse(){

        Student student = new Student("Radhe");
        student.setAddress(new Address(111, "Neta Ji Road", "Gurugram"));
        Course course = new Course("Kubernetes in 100 steps");

        Student saveStudentAndCourse = studentRepository.saveStudentAndCourse(student, course);
        assertEquals("Kubernetes in 100 steps", saveStudentAndCourse.getCourses().get(0).getName());
        assertEquals("Radhe", saveStudentAndCourse.getCourses().get(0).getStudents().get(0).getName());

    }

    @Test
    @DirtiesContext
    @Transactional
    void testSetAddressForExistingStudent(){

        Student student = entityManager.find(Student.class, 20001L);
        student.setAddress(new Address(301, "New MG Road", "Bangalore"));
        entityManager.flush();
        assertNotNull(student.getAddress());
        assertEquals("New MG Road", student.getAddress().getStreet());
        assertEquals("Bangalore", student.getAddress().getCity());

    }

    @Test
    void testGetAddressForExistingStudent(){

        Student student = entityManager.find(Student.class, 20003L);

        assertThat(student)
                .isNotNull()
                .extracting(Student::getAddress)
                .extracting(Address::getStreet, Address::getCity)
                .containsExactly("MG Road", "Bangalore");
    }

}