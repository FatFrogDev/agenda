package org.globant.agenda.agenda.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

import org.globant.agenda.agenda.exceptions.ResourceNotFoundException;
import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Person;
import org.globant.agenda.agenda.repository.CellphoneRepository;
import org.globant.agenda.agenda.repository.PersonRepository;
import org.globant.agenda.agenda.request.PersonAndCellphoneRequest;
import org.globant.agenda.agenda.service.CellphoneService;
import org.globant.agenda.agenda.service.PersonServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
	CellphoneService cellphoneService;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonServiceImpl personServiceImpl;

    @Autowired
	CellphoneRepository cellphoneRepository;

    @PostMapping("/person")
    public ResponseEntity<Person> savePerson(@RequestBody PersonAndCellphoneRequest personAndCellphoneRequest) {
        Person person = personAndCellphoneRequest.getPerson();
        Collection<Cellphone> cellphones = personAndCellphoneRequest.getCellphones();

        for (Cellphone cellphone : cellphones) {
            cellphone.setPerson(person);
        }

        person.setCellphones(cellphones);

        if (personServiceImpl.saveWithOneOrMorePhone(person)) {
            return ResponseEntity.ok(person);
        } else {
           throw new RuntimeException("Error saving person with phones");
        }        
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPerson() {
        List<Person> persons = personRepository.findAll();

        if (persons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(persons, HttpStatus.OK);
        }
    }
    
    @SuppressWarnings("null")
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable Integer id) {
        Person person = personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("El personaje con el siguiente ID no existe: "+id));
        return ResponseEntity.ok(person);
    }

    @SuppressWarnings("null")
    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person updatedPerson) {
        Person existingPerson = personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("El personaje con el siguiente ID no existe: " + id));        

        Collection<Cellphone> cellphones = existingPerson.getCellphones();

        // Copy the propierties of updatedPerson to existingPerson, without the ID
        BeanUtils.copyProperties(updatedPerson, existingPerson, "id", "cellphones");

        existingPerson.setCellphones(cellphones);
        System.out.println("Line 93: "+cellphones);

        Person personUpdated = personRepository.save(existingPerson);
        return ResponseEntity.ok(personUpdated);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/person/{id}")
    public Boolean deletePerson(@PathVariable Integer id) {
        Person existingPerson = personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("El personaje con el siguiente ID no existe: " + id));

        try {
            cellphoneRepository.deleteCellphonesByPersonId(existingPerson.getId());
            personRepository.delete(existingPerson);
            return true;                           
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }
}
