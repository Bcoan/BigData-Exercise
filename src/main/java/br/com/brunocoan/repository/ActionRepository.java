package br.com.brunocoan.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import br.com.brunocoan.domain.Action;
import br.com.brunocoan.domain.Person;
@Repository
public interface ActionRepository extends GraphRepository<Action> {
    @Query("START person=node({0}) " +
            " MATCH (person)-[action:viewed | addedcart | bought]->(product)" +
            " RETURN action "
            + "ORDER BY action.date DESC "
            + "LIMIT {1}")
	List<Action> findByPerson(Person person, Long limit);
    
    @Query("START person=node({0}) " +
            " MATCH (person)-[action:viewed | addedcart | bought]->(product)" +
            " RETURN action "
            + "ORDER BY action.date DESC ")
	List<Action> findByPerson(Person person);
	
}
