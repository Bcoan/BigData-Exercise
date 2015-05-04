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

import br.com.brunocoan.domain.Action;
import br.com.brunocoan.domain.Person;
import br.com.brunocoan.service.ActionService;

@RestController
@RequestMapping("/people")
public class ActionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActionController.class);
	
	@Autowired
	private ActionService service;

	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value="/{personId}/viewed/{productId}", method=RequestMethod.POST)
	public void viewedAction(@PathVariable Long personId, @PathVariable Long productId) {
		LOGGER.info("person {} viewed product {}", personId, productId);
		service.createViewedAction(personId, productId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value="/{personId}/added-to-cart/{productId}", method=RequestMethod.POST)
	public void addedCartAction(@PathVariable Long personId, @PathVariable Long productId) {
		LOGGER.info("person {} added product {} to cart", personId, productId);
		service.createAddedCartAction(personId, productId);
	}
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value="/{personId}/bought/{productId}", method=RequestMethod.POST)
	public void boughtAction(@PathVariable Long personId, @PathVariable Long productId) {
		LOGGER.info("person {} bought product {}", personId, productId);
		service.createBoughtAction(personId, productId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{personId}/actions", method=RequestMethod.GET)
	public Collection<Action> actionsByPerson(@PathVariable Long personId,@RequestParam(value="limit", required= true) Long limit) {
		LOGGER.info("loading actions from person {}", personId);
		return service.actionsByPerson(personId, limit);
	}
}
