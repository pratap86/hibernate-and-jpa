package com.pratap.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
//@IdClass(CustomerId.class)
@Data
@AllArgsConstructor
public class Customer {

    @EmbeddedId
    private CustomerId customerId;
    private String name;

    protected Customer(){}


}
