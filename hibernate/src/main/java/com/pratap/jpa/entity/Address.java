package com.pratap.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
public class Address {

    @Getter
    @Setter
    private int houseNum;

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private String city;

    protected Address(){}
}
