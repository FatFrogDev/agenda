package org.globant.agenda.agenda.service;

import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.repository.CellphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.Iterator;
import java.util.List;
import java.util.Optional;
import  java.util.Collection;

@Service
public class CellphoneServiceImpl  implements CellphoneService{

    @Autowired
    CellphoneRepository repository;

    @Override
    public boolean isSaveableCollection(Collection<Cellphone> cellhpones) {
        Iterator phoneIterator = cellhpones.iterator();
        boolean result = true;
        while (phoneIterator.hasNext()) {
            final Cellphone phone = (Cellphone) phoneIterator.next();
            result = checkPhoneExists(phone.getNumber());
            if (result){
                return false;
            }
        }
        return result;
    }

    @Override
    public void SaveCollection(Collection<Cellphone> cellhpones) {
            for(Cellphone cellphone:cellhpones){
                repository.save(cellphone);
            }
    }

    @Override
    public List<Cellphone> findAll() {
        return  repository.findAll();
    }

    @Override
    public Optional<Cellphone> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Cellphone> findByPhoneNumber(String phoneNumber) {
        return repository.findByNumber(phoneNumber);
    }

    @Override
    public void save(Cellphone cellphone) {
        repository.save(cellphone);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public boolean checkPhoneExists(String number) {
        return repository.checkPhoneExists(number);
    }
}
