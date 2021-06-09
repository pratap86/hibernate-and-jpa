package com.pratap.jpa.repository;

import com.pratap.jpa.entity.Customer;
import com.pratap.jpa.entity.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, CustomerId> {
}
