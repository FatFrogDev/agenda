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
    @SuppressWarnings("rawtypes")
    public boolean isSaveableCollection(Collection<Cellphone> cellhpones) {
        final Iterator phoneIterator = cellhpones.iterator();
        boolean result = true;
        while (phoneIterator.hasNext()) {
            final Cellphone phone = (Cellphone) phoneIterator.next();
            result = checkPhoneExists(phone.getNumber());
            if (result){
                System.out.println("Repeated phone: " + phone.getNumber());
                return false;
            }else result = true;
        }
        return result;
    }

    @SuppressWarnings("null")
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

    @SuppressWarnings("null")
    @Override
    public Optional<Cellphone> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Cellphone> findByPhoneNumber(String phoneNumber) {
        return repository.findByNumber(phoneNumber);
    }

    @SuppressWarnings("null")
    @Override
    public void save(Cellphone cellphone) {
        repository.save(cellphone);
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public boolean checkPhoneExists(String number) {
        return repository.checkPhoneExists(number);
    }
}
