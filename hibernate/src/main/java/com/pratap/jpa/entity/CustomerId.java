package com.pratap.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * CustomerId a special class to represent composite Primary Key
 * that implements the Serializable
 * To avoid redefine id and email fields in Customer(@IdClass), use @Embeddable here
 * @author Pratap Narayan
 */
@Embeddable
@AllArgsConstructor
@Data
public class CustomerId implements Serializable {

    private Long id;

    private String email;

    protected CustomerId(){}
}
