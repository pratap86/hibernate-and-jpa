package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Employee;
import com.pratap.jpa.entity.FullTimeEmployee;
import com.pratap.jpa.entity.PartTimeEmployee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HibernateApplication.class)
class EmployeeRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DirtiesContext
    void testSaveEmployee(){

        Employee partTimeEmployee = new PartTimeEmployee("Jacob", new BigDecimal(60));
        Employee savedPartTimeEmployee = employeeRepository.save(partTimeEmployee);
        assertNotNull(savedPartTimeEmployee.getId());
        Employee fullTimeEmployee = new FullTimeEmployee("Neil", new BigDecimal(60000));
        Employee savedFullTimeEmployee = employeeRepository.save(fullTimeEmployee);
        assertNotNull(savedFullTimeEmployee.getId());

    }

    @Test
    void testGetFullTimeEmployee(){

        List<FullTimeEmployee> allFullTimeEmployees = employeeRepository.getAllFullTimeEmployees();
        assertEquals(3, allFullTimeEmployees.size());
    }

    @Test
    void testGetPartTimeEmployee(){

        List<PartTimeEmployee> allPartTimeEmployees = employeeRepository.getAllPartTimeEmployees();
        assertEquals(2, allPartTimeEmployees.size());
    }
}