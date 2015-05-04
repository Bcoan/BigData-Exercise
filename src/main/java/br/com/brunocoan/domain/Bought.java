package br.com.brunocoan.domain;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

@RelationshipEntity(type="bought")
public class Bought extends Action {

	public Bought() {
		super("bought");
	}

}
