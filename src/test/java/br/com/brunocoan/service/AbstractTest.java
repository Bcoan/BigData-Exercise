package br.com.brunocoan.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.brunocoan.Application;
import br.com.brunocoan.domain.Person;
import br.com.brunocoan.domain.Product;
import br.com.brunocoan.repository.PersonRepository;
import br.com.brunocoan.repository.ProductRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public abstract class AbstractTest {
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PersonRepository personRepository;

	protected MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		insertDataForTest();
	}
	
	private void insertDataForTest() {
		productRepository.save(new Product(0l, "tv", 1200d));
		productRepository.save(new Product(1l, "dvd", 200d));
		productRepository.save(new Product(02l, "smartphone", 1500d));
		
		personRepository.save(new Person(0l,"jose@mail.com", "jose"));
		personRepository.save(new Person(1l,"maria@mail.com", "maria"));
		personRepository.save(new Person(2l, "paulo@mail.com", "paulo"));
	}

}
