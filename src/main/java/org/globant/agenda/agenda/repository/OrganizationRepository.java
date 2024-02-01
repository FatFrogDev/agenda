package org.globant.agenda.agenda.repository;

import org.globant.agenda.agenda.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
