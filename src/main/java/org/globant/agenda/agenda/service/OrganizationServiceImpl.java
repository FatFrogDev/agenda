package org.globant.agenda.agenda.service;

import org.globant.agenda.agenda.model.Organization;
import org.globant.agenda.agenda.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Optional<Organization> findById(Integer id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization;
    }

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public void delete(Integer id) {
        organizationRepository.deleteById(id);
    }

}
