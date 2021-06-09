package com.pratap.jpa.repository;

import com.pratap.jpa.entity.Contributor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributorRepository {

    @Procedure(name = "Contributor.getAllContributors")
    List<Contributor> getAllContributors();
}
