package br.com.brunocoan.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Person {
	@GraphId
	private Long nodeId;
	@Indexed(unique = true,indexType = IndexType.FULLTEXT, indexName = "person_id")
	private Long personId;
	private String email;
	private String name;

	public Person() {	}
	
	public Person(Long personId) {
		super();
		this.personId = personId;
	}

	public Person(Long personId, String email, String name) {
		super();
		this.personId = personId;
		this.email = email;
		this.name = name;
	}
	
	public Person(Long id,Long personId, String email, String name) {
		super();
		this.nodeId = id;
		this.personId = personId;
		this.email = email;
		this.name = name;
	}
	
	
	public Long getNodeId() {
		return nodeId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [id=" + nodeId + ", personId=" + personId + ", email="
				+ email + ", name=" + name + "]";
	}
	
	
}
