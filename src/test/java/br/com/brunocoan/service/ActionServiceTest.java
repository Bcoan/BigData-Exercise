package br.com.brunocoan.service;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.brunocoan.domain.Action;
import br.com.brunocoan.exception.NotFoundException;

public class ActionServiceTest extends AbstractTest {
	@Autowired
	private ActionService service;
	
	@Test
	public void testCreateViewedAction() {
		service.createViewedAction(1l, 1l);
	}
	@Test(expected=NotFoundException.class)
	public void testCreateViewedActionInvalidPersonId() {
		service.createViewedAction(1111l, 1l);
	}
	@Test(expected=NotFoundException.class)
	public void testCreateViewedActionInvalidProductId() {
		service.createViewedAction(1l, 1111l);
	}
	@Test
	public void testFindUserActions() {
		service.createViewedAction(0l, 0l);
		service.createViewedAction(0l, 1l);
		service.createViewedAction(0l, 2l);
		
		Collection<Action> actions = service.actionsByPerson(0l, 0l);

    	assertThat(actions, hasSize(3));
	}
	
	@Test
	public void testFindUserActionsLimit2() {
		service.createViewedAction(1l, 0l);
		service.createViewedAction(1l, 1l);
		service.createViewedAction(1l, 2l);
		
		Collection<Action> actions = service.actionsByPerson(1l, 2l);

    	assertThat(actions, hasSize(2));
	}
	
}
