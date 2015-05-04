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

import br.com.brunocoan.domain.Product;
import br.com.brunocoan.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method=RequestMethod.POST)
	public void create(@RequestParam(value="productId") Long productId,
			@RequestParam(value="name") String name,
			@RequestParam(value="price") Double price) {
		
		Product product = new Product(productId, name, price);
		LOGGER.info("creating product {}", product);
		service.createProduct(product);
	}
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Product> listProduct(@RequestParam(value="limit", required= false) Long limit) {
		LOGGER.info("listing products limit {}", limit);
		return service.listProduct(limit);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{productId}", method=RequestMethod.GET)
	public void findByPersonId(@PathVariable Long productId) {
		LOGGER.info("loading product from productId {}", productId);
		service.findByProductId(productId);
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value="/{productId}", method=RequestMethod.DELETE)
	public void deletePerson(@PathVariable Long productId) {
		LOGGER.info("deleting product from productId {}", productId);
		service.deleteProduct(productId);
	}


}
