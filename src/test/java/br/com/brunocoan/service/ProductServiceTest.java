package br.com.brunocoan.service;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.brunocoan.domain.Product;
import br.com.brunocoan.exception.DuplicatedException;
import br.com.brunocoan.exception.NotFoundException;

public class ProductServiceTest extends AbstractTest {
	
	@Autowired
	protected ProductService service;

	@Test
	public void testSaveProduct() {
		Product product = new Product(3l,"teste",2.20);
		
		service.createProduct(product);
    	assertThat(product.getNodeId(), notNullValue());

	}
	@Test
	public void testGetById() {
		Product product = new Product(4l,"iphone",2.00);
		service.createProduct(product);
		Product found = service.findByProductId(product.getProductId());
		
    	assertThat(product.getNodeId(), is(equalTo(found.getNodeId())));
    	assertThat(product.getProductId(), is(equalTo(found.getProductId())));
    	assertThat(product.getName(), is(equalTo(found.getName())));
    	assertThat(product.getPrice(), is(equalTo(found.getPrice())));

	}
    @Test(expected = DuplicatedException.class)
	public void testSaveDuplicatedProduct() {
		Product product = new Product(0l,"teste",2.20);
		
		service.createProduct(product);
	}
    
    @Test
    public void testListProductLimit2() {
    	Collection<Product> products = service.listProduct(2l);
    	assertThat(products, hasSize(2));
    }
    
    @Test(expected=NotFoundException.class)
    public void testDeleteInvalidPerson() {
    	service.deleteProduct(1234l);;
    }

}
