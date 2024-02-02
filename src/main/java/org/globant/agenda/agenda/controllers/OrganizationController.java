package org.globant.agenda.agenda.controllers;

import java.util.List;

import org.globant.agenda.agenda.exceptions.ResourceNotFoundException;
import org.globant.agenda.agenda.model.Organization;
import org.globant.agenda.agenda.model.Person;
import org.globant.agenda.agenda.repository.OrganizationRepository;
import org.globant.agenda.agenda.repository.PersonRepository;
import org.globant.agenda.agenda.request.OrganizationAndPersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.lang.NonNull;

@RestController
@RequestMapping("/api/v1")
public class OrganizationController {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    PersonRepository personRepository;

    @SuppressWarnings("null")
    @Transactional
    @PostMapping("/organization")
    public ResponseEntity<Organization> saveOrganizationWithManager(@RequestBody OrganizationAndPersonRequest organizationAndPersonRequest) {
        try {

            Person person = organizationAndPersonRequest.getPerson();
            Organization organization = organizationAndPersonRequest.getOrganization();            

            // Save the new person (manager) in the database
            Person savedPerson = personRepository.save(person);

            // set the manager person at the organization register
            organization.setPerson(savedPerson);            
            Organization savedOrganization = organizationRepository.save(organization);

            return ResponseEntity.ok(savedOrganization);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/organization")
    public ResponseEntity<List<Organization>> getAllOrganizationsWithPersons() {
        List<Organization> organizations = organizationRepository.findAllWithPersons();

        if (organizations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(organizations, HttpStatus.OK);
        }
    }
    
    @SuppressWarnings("null")
    @GetMapping("/organization/{id}")
    public ResponseEntity<Organization> getOrganizationByID(@PathVariable Integer id) {
        Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("La organizacion con el siguiente ID no existe: "+id));
        return ResponseEntity.ok(organization);
    }
    
    @SuppressWarnings("null")
    @PutMapping("/organization/{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable @NonNull Integer id, @RequestBody Organization updatedOrganization) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La organización con el siguiente ID no existe: " + id));

        // Update the atributes necessaries without the "Person"
        existingOrganization.setEnterpriseName(updatedOrganization.getEnterpriseName());
        existingOrganization.setCity(updatedOrganization.getCity());
        existingOrganization.setDomain(updatedOrganization.getDomain());

        // Save the entity updated
        Organization organizationUpdated = organizationRepository.save(existingOrganization);

        return ResponseEntity.ok(organizationUpdated);
    }


}
