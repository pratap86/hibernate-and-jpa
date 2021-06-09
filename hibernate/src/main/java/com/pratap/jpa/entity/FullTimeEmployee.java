package com.pratap.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "get_all_full_time_employees", query = "select e from FullTimeEmployee e")
})
public class FullTimeEmployee extends Employee {

    @Getter
    @Setter
    private BigDecimal salary;

    protected FullTimeEmployee(){}

    public FullTimeEmployee(String name, BigDecimal salary){
        super(name);
        this.salary = salary;
    }
}
