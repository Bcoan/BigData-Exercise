package br.com.brunocoan.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import br.com.brunocoan.service.AbstractTest;

public class ActionControllerTest extends AbstractTest {
	
	@Test
	public void testCreateViewedAction() throws Exception {
		this.mvc.perform(post("/people/{personId}/viewed/{productId}", "0","0")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testCreateAddedCartAction() throws Exception {
		this.mvc.perform(post("/people/{personId}/added-to-cart/{productId}", "0","0")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testCreateBoughtAction() throws Exception {
		this.mvc.perform(post("/people/{personId}/bought/{productId}", "0","0")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

   
}
