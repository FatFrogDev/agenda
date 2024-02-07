package org.globant.agenda.agenda.service;

import java.util.List;
import java.util.Optional;

import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.globant.agenda.agenda.repository.PersonRepository;
import java.util.Collection;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CellphoneServiceImpl cellphoneServiceImpl = new CellphoneServiceImpl();

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Person> findById(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        return person;
    }

    @SuppressWarnings("null")
    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public boolean savePerson(Person person)  {   
        return personRepository.savePerson(
                person.getAddress(),
                person.isManager(),
                person.getLastname(),
                person.getName()
        );
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        personRepository.deleteById(id);
    }

    /**
     * Saves or not a person and his phone collection when both are not registered in the database.
     * @param person
     * @return Boolean value declaring weather the person and his phone/phones can be persisted into the database or not.
     */
    @Override
    public boolean saveWithOneOrMorePhone(Person person) {
        boolean canSavePhones = cellphoneServiceImpl.isSaveableCollection(person.getCellphones());
        if (canSavePhones){
            Collection<Cellphone> phones = person.getCellphones();
            if(savePerson(person)){
                Person savedPerson = getLastPerson();
                
                for (Cellphone phone:phones){
                    phone.setPerson(savedPerson);
                }

                cellphoneServiceImpl.SaveCollection(phones);
                return true;

            }else {
                System.out.println("Error saving the person");    
                return false;
            }
        }else{
            System.out.println("Can't save the phones collection");
            return false;            
        }
    }

    @Override
    public Person getLastPerson() {
        return personRepository.getLast();
    }

}
