package org.globant.agenda.agenda.repository;

import java.util.Optional;

import org.globant.agenda.agenda.model.Cellphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface CellphoneRepository extends JpaRepository<Cellphone,Integer> {

    Optional <Cellphone> findByNumber(String number);

    @Query(nativeQuery = true, value = "select agenda.check_phone_exists(:in_phone)")
    boolean checkPhoneExists(
            @Param("in_phone") String in_phone
    );

    @Transactional
    @Modifying
    @Query(value = "delete from Cellphone c where c.person.id = :personId")
    void deleteCellphonesByPersonId(@Param("personId") Integer personId);
}
