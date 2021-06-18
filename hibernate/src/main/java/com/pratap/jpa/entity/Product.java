package com.pratap.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import java.math.BigDecimal;

@Entity
@ToString
@NamedStoredProcedureQuery(name = "GetAllProducts", procedureName = "GetAllProducts", resultClasses = {Product.class})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String  name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private BigDecimal price;

    protected Product(){}
}
