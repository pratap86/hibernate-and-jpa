package com.pratap.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ToString
public class Review {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private int rating;

    @Getter
    @Setter
    private String description;

    protected Review(){}

    public Review(int rating, String description){
        this.rating = rating;
        this.description = description;
    }
}
