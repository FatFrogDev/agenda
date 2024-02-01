package org.globant.agenda.agenda.repository;

import  org.globant.agenda.agenda.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    
}
