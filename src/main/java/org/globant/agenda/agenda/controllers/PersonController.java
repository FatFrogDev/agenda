package org.globant.agenda.agenda.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    
import java.util.List;

import org.globant.agenda.agenda.exceptions.ResourceNotFoundException;
import org.globant.agenda.agenda.model.Person;
import org.globant.agenda.agenda.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @SuppressWarnings("null")
    @PostMapping("/person")
    public Person savePerson(@RequestBody Person person) {
        return personRepository.save(person);
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

        // Copy the propierties of updatedPerson to existingPerson, without the ID
        BeanUtils.copyProperties(updatedPerson, existingPerson, "id");

        Person personUpdated = personRepository.save(existingPerson);
        return ResponseEntity.ok(personUpdated);
    }
}
