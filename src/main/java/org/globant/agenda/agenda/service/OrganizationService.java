package org.globant.agenda.agenda.service;

import org.globant.agenda.agenda.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

    List<Organization> findAll();

    Optional<Organization> findById(Integer id);

    void save(Organization organization);

    void delete(Integer id);
}
