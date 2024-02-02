package org.globant.agenda.agenda.repository;

import org.globant.agenda.agenda.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.person")
    List<Organization> findAllWithPersons();
}
