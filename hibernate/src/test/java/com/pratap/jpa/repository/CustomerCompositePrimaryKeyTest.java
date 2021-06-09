package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Customer;
import com.pratap.jpa.entity.CustomerId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HibernateApplication.class)
@ExtendWith(SpringExtension.class)
class CustomerCompositePrimaryKeyTest {


    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveCustomer(){

        CustomerId customerId = new CustomerId(70001L, "test@eby.com");
        Customer customer = new Customer(customerId, "Test");
        Customer savedCustomer = customerRepository.save(customer);
        assertEquals("Test", savedCustomer.getName());
        assertEquals(70001L, savedCustomer.getCustomerId().getId());
        assertEquals("test@eby.com", savedCustomer.getCustomerId().getEmail());
    }

}