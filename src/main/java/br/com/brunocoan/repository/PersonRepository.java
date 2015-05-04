package br.com.brunocoan.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import br.com.brunocoan.domain.Person;
@Repository
public interface PersonRepository extends GraphRepository<Person> {
	
	public Person findByPersonId(Long personId);

}
