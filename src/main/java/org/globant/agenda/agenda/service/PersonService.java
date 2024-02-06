package org.globant.agenda.agenda.service;

import java.util.List;
import java.util.Optional;

import org.globant.agenda.agenda.model.Person;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> findById(Integer id);

    void save(Person person);

    void delete(Integer id);

    boolean savePerson(Person person);

    boolean saveWithOneOrMorePhone(Person person);

    Person getLastPerson();
}