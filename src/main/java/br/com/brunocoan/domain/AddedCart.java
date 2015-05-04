package br.com.brunocoan.domain;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

@RelationshipEntity(type = "addedcart")
public class AddedCart extends Action {

	public AddedCart() {
		super("added-to-cart");
	}

}
