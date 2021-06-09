package com.pratap.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "get_all_part_time_employees", query = "select e from PartTimeEmployee e")
})
public class PartTimeEmployee extends Employee {

    @Getter
    @Setter
    private BigDecimal hourlyWage;

    protected PartTimeEmployee(){}

    public PartTimeEmployee(String name, BigDecimal hourlyWage){
        super(name);
        this.hourlyWage = hourlyWage;
    }
}
