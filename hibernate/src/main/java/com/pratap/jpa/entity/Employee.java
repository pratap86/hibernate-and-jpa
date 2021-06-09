package com.pratap.jpa.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@MappedSuperclass
@ToString
public abstract class Employee {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Employee(){}

    public Employee(String name){
        this.name = name;
    }
}
