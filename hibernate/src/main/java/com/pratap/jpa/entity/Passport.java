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
public class Passport {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private String passportNumber;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    // mappedBy define in Passport side so Student is owning side the relationship
    // in that way, student_id column would not be created in PASSPORT table
    private Student student;

    protected Passport(){}

    public Passport(String passportNumber){
        this.passportNumber = passportNumber;
    }
}
