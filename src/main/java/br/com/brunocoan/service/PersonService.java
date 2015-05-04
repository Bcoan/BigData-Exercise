package br.com.brunocoan.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brunocoan.domain.Person;
import br.com.brunocoan.exception.DuplicatedException;
import br.com.brunocoan.exception.NotFoundException;
import br.com.brunocoan.repository.ActionRepository;
import br.com.brunocoan.repository.PersonRepository;

@Service
@Transactional
public class PersonService {
	@Autowired
	private PersonRepository repository;
	@Autowired
	private ProductService productService;
	@Autowired
	private ActionRepository actionRepository;
	
	public void createPerson(Person person) {
		verifyIfExists(person.getPersonId());
		repository.save(person);
	}

	
	public void delete(Long personId) {
		Person person = repository.findByPersonId(personId);
		if(Objects.isNull(person)) {
			throw new NotFoundException("Person not found");
		}
		repository.delete(person);
	}
	public Collection<Person> listPerson(Long limit) {
		if(limit > 0) {
			Page<Person> page = repository.findAll(new PageRequest(0, limit.intValue()));
			return page.getContent();
		}
		
		return repository.findAll().as(ArrayList.class);
	}
	
	public Person findByPersonId(Long personId) {
		Person person = repository.findByPersonId(personId);
		if(Objects.isNull(person)) {
			throw new NotFoundException("Person not found");
		}
		return person;
	}
	private void verifyIfExists(Long personId) {
		Person result = repository.findByPersonId(personId);
		if(Objects.nonNull(result)) {
			throw new DuplicatedException(String.format("Person %s already created", personId));
		}
	}


	}
