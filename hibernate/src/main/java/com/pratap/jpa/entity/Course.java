package com.pratap.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDateTime;

@Entity
@ToString
@NamedQueries(value = {
        @NamedQuery(name = "get_all_course_query", query = "Select c from Course c"),
        @NamedQuery(name = "get_100_steps_query", query = "Select c from Course c where c.name like '%100 steps'")
})
public class Course {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    protected Course(){}

    public Course(String name){
        this.name = name;
    }
}
