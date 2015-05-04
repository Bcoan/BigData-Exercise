package br.com.brunocoan.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.brunocoan.domain.Person;
import br.com.brunocoan.exception.DuplicatedException;
import br.com.brunocoan.exception.NotFoundException;

public class PersonServiceTest extends AbstractTest {
	
	@Autowired
	protected PersonService service;

	@Test
	public void testSavePerson() {
		Person person = new Person(3l,"bruno@mail2.com", "bruno");
		
		service.createPerson(person);
    	assertThat(person.getNodeId(), notNullValue());

	}
    @Test(expected = DuplicatedException.class)
	public void testSaveDuplicatedPerson() {
		Person person = new Person(0l,"bruno@mail2.com", "bruno");
		
		service.createPerson(person);
	}

	@Test
	public void testGetById() {
		Person person = new Person(4l,"bruno@mail2.com", "bruno");
		service.createPerson(person);
		Person found = service.findByPersonId(person.getPersonId());
		
    	assertThat(person.getNodeId(), is(equalTo(found.getNodeId())));
    	assertThat(person.getPersonId(), is(equalTo(found.getPersonId())));
    	assertThat(person.getName(), is(equalTo(found.getName())));
    	assertThat(person.getEmail(), is(equalTo(found.getEmail())));

	}
    
    @Test
    public void testListPersonsLimit2() {
    	Collection<Person> persons = service.listPerson(2l);
    	assertThat(persons, hasSize(2));
    }
    
    @Test(expected=NotFoundException.class)
    public void testDeleteInvalidPerson() {
    	service.delete(13354l);
    }
    
}
