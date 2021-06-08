package com.pratap.jpa.repository;

import com.pratap.jpa.entity.Employee;
import com.pratap.jpa.entity.FullTimeEmployee;
import com.pratap.jpa.entity.PartTimeEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
public class EmployeeRepository {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public Employee save(Employee employee){
        log.info("Going to Execute save() with Employee = {}", employee);
        entityManager.persist(employee);
        log.info("Employee saved, Employee = {}", employee);
        return employee;
    }

    public List<PartTimeEmployee> getAllPartTimeEmployees(){

        log.info("Going to execute getAllPartTimeEmployees()");
        return entityManager.createNamedQuery("get_all_part_time_employees", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> getAllFullTimeEmployees(){

        log.info("Going to execute getAllFullTimeEmployees()");
        return entityManager.createNamedQuery("get_all_full_time_employees", FullTimeEmployee.class).getResultList();
    }
}
