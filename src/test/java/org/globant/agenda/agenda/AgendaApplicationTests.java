package org.globant.agenda.agenda;

import org.globant.agenda.agenda.repository.CellphoneRepository;
import org.globant.agenda.agenda.repository.PersonRepository;
import org.globant.agenda.agenda.service.CellphoneService;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collection;
import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class AgendaApplicationTests {

	@Autowired
	CellphoneService cellphoneService;

	@Autowired
	PersonRepository personRepository;

	private static final Logger logger = LoggerFactory.getLogger(AgendaApplication.class); 
	
	@Test
	void contextLoads() {
		SavePhoneCollection();
	}

	@Test
	void SavePhoneCollection(){
		Collection <Cellphone> phones = new ArrayList<Cellphone>(10);
		Optional <Person> person =  personRepository.findById(1);
		Cellphone cellphone = new Cellphone(person.get(), "1111111111");
		Cellphone cellphone0 = new Cellphone(person.get(), "2222222222");
		Cellphone cellphone1 = new Cellphone(person.get(), "3333333333");
		Cellphone cellphone2 = new Cellphone(person.get(), "4444444444");
		Cellphone cellphone3 = new Cellphone(person.get(), "5555555555");

		phones.add(cellphone);
		phones.add(cellphone0);
		phones.add(cellphone1);
		phones.add(cellphone2);
		phones.add(cellphone3);

		if (cellphoneService.isSaveableCollection(phones)){
			cellphoneService.SaveCollection(phones);
		} else  System.out.println("The collection can not be saved due a repeated number, try modifiying the list and try again");
	}

}
