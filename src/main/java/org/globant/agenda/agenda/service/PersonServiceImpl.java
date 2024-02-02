package org.globant.agenda.agenda.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.globant.agenda.agenda.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.globant.agenda.agenda.repository.PersonRepository;

public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> findById(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        return person;
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }



    @Override
    public boolean savePerson(Person person)  {
        return personRepository.savePerson(
                person.getAddress(),
                Arrays.toString(person.getCellphone() ),
                person.isManager(),
                person.getLastname(),
                person.getName()
        );
    }

    @Override
    public void delete(Integer id) {
        personRepository.deleteById(id);
    }

}
