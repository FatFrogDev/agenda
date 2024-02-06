package org.globant.agenda.agenda.repository;

import  org.globant.agenda.agenda.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(nativeQuery = true, value = "select agenda.save_person(:in_address, :in_is_manager, :in_lastname, :in_name)")
    boolean savePerson(
            @Param("in_address") String in_address,
            @Param("in_is_manager") boolean in_is_manager,
            @Param("in_lastname") String in_lastname,
            @Param("in_name") String in_name
    );

    @Query("SELECT p FROM Person p ORDER BY p.id DESC LIMIT 1")
    Person getLast();
}
