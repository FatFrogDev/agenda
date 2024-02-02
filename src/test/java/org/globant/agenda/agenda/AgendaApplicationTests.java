package org.globant.agenda.agenda;

import org.globant.agenda.agenda.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.globant.agenda.agenda.model.Person;

@SpringBootTest
class AgendaApplicationTests {


	@Autowired
	PersonRepository repo;

	@Test
	void contextLoads() {

	}

}
