package com.pratap.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@ToString
public class Student {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Passport passport;

    protected Student(){}

    public Student(String name){
        this.name = name;
    }
}
