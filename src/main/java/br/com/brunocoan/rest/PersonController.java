package br.com.brunocoan.rest;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunocoan.domain.Person;
import br.com.brunocoan.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService service;
	

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method=RequestMethod.POST)
	public void create(@RequestParam(value="personId", required= true) Long personId,
			@RequestParam(value="email", required= true) String email,
			@RequestParam(value="name", required= true) String name) {
		
		Person person = new Person(personId, email, name);
		
		LOGGER.info("creating person {}", person);
		service.createPerson(person);
	}
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Person> listPerson(@RequestParam(value="limit", required= true) Long limit) {
		LOGGER.info("listing persons limit {}", limit);
		return service.listPerson(limit);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{personId}", method=RequestMethod.GET)
	public Person findByPersonId(@PathVariable Long personId) {
		LOGGER.info("loading person from personId {}", personId);
		return service.findByPersonId(personId);
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value="/{personId}", method=RequestMethod.DELETE)
	public void deletePerson(@PathVariable Long personId) {
		LOGGER.info("deleting person from personId {}", personId);
		service.delete(personId);
	}
	
}
