package org.globant.agenda.agenda.controllers;

import java.util.Collection;
import java.util.List;

import org.globant.agenda.agenda.exceptions.ResourceNotFoundException;
import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Organization;
import org.globant.agenda.agenda.model.Person;
import org.globant.agenda.agenda.repository.CellphoneRepository;
import org.globant.agenda.agenda.repository.OrganizationRepository;
import org.globant.agenda.agenda.repository.PersonRepository;
import org.globant.agenda.agenda.request.OrganizationAndPersonAndCellphonesRequest;
import org.globant.agenda.agenda.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Autowired
    PersonServiceImpl personServiceImpl;

    @Autowired
	CellphoneRepository cellphoneRepository;
    
    @Transactional
    @PostMapping("/organization")
    public ResponseEntity<Organization> saveOrganizationWithManager(@RequestBody OrganizationAndPersonAndCellphonesRequest organizationAndPersonAndCellphoneRequest) {
        try {            

            Person person = organizationAndPersonAndCellphoneRequest.getPerson();
            Organization organization = organizationAndPersonAndCellphoneRequest.getOrganization();    
            Collection<Cellphone> cellphones = organizationAndPersonAndCellphoneRequest.getCellphone();

            // Set the person to all cellphones of the collection
            for (Cellphone cellphone : cellphones) {
                cellphone.setPerson(person);
            }

            person.setCellphones(cellphones); // Set the list of cellphones to the person

            if (personServiceImpl.saveWithOneOrMorePhone(person)) {
                Person personSaved = personRepository.getLast();
                // Create the organization with the last person
                organization.setPerson(personSaved);            
                Organization savedOrganization = organizationRepository.save(organization);

                return ResponseEntity.ok(savedOrganization);
            } else {
                throw new RuntimeException("Error saving person with phones");
            }
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

    @SuppressWarnings("null")
    @DeleteMapping("/organization/{id}")
    public Boolean deleteOrganization(@PathVariable @NonNull Integer id) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La organización con el siguiente ID no existe: " + id));

        try {
            cellphoneRepository.deleteCellphonesByPersonId(existingOrganization.getPerson().getId());
            organizationRepository.delete(existingOrganization);            
            personRepository.delete(existingOrganization.getPerson());
            return true;
        } catch (Exception e) {
            System.out.println("ERROR DE TIPO"+e.toString());
            return false;
        }
    }

}
