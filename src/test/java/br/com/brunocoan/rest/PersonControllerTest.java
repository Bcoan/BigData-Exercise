package br.com.brunocoan.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import br.com.brunocoan.service.AbstractTest;

public class PersonControllerTest extends AbstractTest {

	
	@Test
	public void testCreate() throws Exception {
		this.mvc.perform(post("/people").param("personId", "5").param("name", "teste")
				.param("email", "teste@mail.com")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

    @Test
    public void testCreateWithoutPersonIdParam() throws Exception {
		this.mvc.perform(post("/people").param("name", "teste")
				.param("email", "teste@mail.com")
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testCreateDuplicatedPerson() throws Exception {
    	this.mvc.perform(post("/people").param("personId", "0").param("name", "teste")
				.param("email", "teste@mail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
    
    
    @Test
    public void testDelete() throws Exception {
        this.mvc.perform(delete("/people/{personId}", "0")).andExpect(status().isNoContent());
    }
    
    @Test
    public void testDeleteInvalidPersonId() throws Exception {
        this.mvc.perform(delete("/people/{personId}", "24443435")).andExpect(status().isNotFound());
    }
    
	@Test
	public void testListAll() throws Exception {

		this.mvc.perform(get("/people").param("limit", "0")).andExpect(status().isOk());
	}
	
	@Test
	public void testListLimit2() throws Exception {

		this.mvc.perform(get("/people").param("limit", "2")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}
    
    
}
