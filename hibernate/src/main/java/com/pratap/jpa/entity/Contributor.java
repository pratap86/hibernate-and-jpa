package com.pratap.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;

@Entity
@ToString
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Contributor.getAllContributors",
                procedureName = "Get_All_Contributors",
                resultClasses = {Contributor.class}
        )
})
public class Contributor {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String contributorId;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String city;

    protected Contributor(){}

    public Contributor(String contributorId, String firstName, String lastName, String email, String city) {
        this.contributorId = contributorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
    }
}
