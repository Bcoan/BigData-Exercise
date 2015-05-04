package br.com.brunocoan.domain;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

@RelationshipEntity(type = "viewed")
public class Viewed extends Action {

	public Viewed() {
		super("viewed");
	}

}
