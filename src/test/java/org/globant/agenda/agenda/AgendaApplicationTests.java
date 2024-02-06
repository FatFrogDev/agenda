package org.globant.agenda.agenda;

import org.globant.agenda.agenda.repository.PersonRepository;
import org.globant.agenda.agenda.service.CellphoneService;
import org.globant.agenda.agenda.service.PersonServiceImpl;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Collection;
import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AgendaApplicationTests {

	@Autowired
	CellphoneService cellphoneService;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@Test
	void contextLoads() {
		SavePhoneCollection();
	}

	@Test
	void SavePhoneCollection(){
		Collection <Cellphone> phones = new ArrayList<Cellphone>(5);
		Optional <Person> person =  personRepository.findById(1);
		Cellphone cellphone = new Cellphone(person.get(), "3112014551");
		Cellphone cellphone0 = new Cellphone(person.get(), "3112014552");
		Cellphone cellphone1 = new Cellphone(person.get(), "3112014553");
		Cellphone cellphone2 = new Cellphone(person.get(), "3112014554");
		Cellphone cellphone3 = new Cellphone(person.get(), "3112014555");

		phones.add(cellphone);
		phones.add(cellphone0);
		phones.add(cellphone1);
		phones.add(cellphone2);
		phones.add(cellphone3);

		if (cellphoneService.isSaveableCollection(phones)){
			cellphoneService.SaveCollection(phones);
		} else  System.out.println("The collection can not be saved due a repeated number, try modifiying the list and try again");
	}


	@Test
	void savePersonWithPhones(){
		Collection <Cellphone> phones = new ArrayList<Cellphone>(5);
				
		Person person = new Person("Test", "2", "2 new address()", null, false);

		Cellphone cellphone = new Cellphone(person, "1234567898");
		Cellphone cellphone0 = new Cellphone(person, "1234567811");
		Cellphone cellphone1 = new Cellphone(person, "3334567899");
		Cellphone cellphone2 = new Cellphone(person, "1234526381");
		Cellphone cellphone3 = new Cellphone(person, "1232567812");

		phones.add(cellphone);
		phones.add(cellphone0);
		phones.add(cellphone1);
		phones.add(cellphone2);
		phones.add(cellphone3);

		person.setCellphones(phones);

		System.out.println("Test passed : "+  personServiceImpl.saveWithOneOrMorePhone(person) ); 
	}
}
