package org.globant.agenda.agenda.service;

import java.util.Optional;
import java.util.List;
import org.globant.agenda.agenda.model.Cellphone;

import java.util.Collection;

public interface CellphoneService {

    void SaveCollection(Collection<Cellphone> cellhpones);
    
    boolean isSaveableCollection(Collection<Cellphone> cellhpones);
    
    List<Cellphone> findAll();

    Optional<Cellphone> findById(Integer id);

    Optional<Cellphone> findByPhoneNumber(String phoneNumber);

    void save(Cellphone cellphone);

    void delete(Integer id);

    boolean checkPhoneExists(String number);
}
