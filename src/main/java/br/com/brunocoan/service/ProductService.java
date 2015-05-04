package br.com.brunocoan.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brunocoan.domain.Product;
import br.com.brunocoan.domain.repository.ProductRepository;
import br.com.brunocoan.exception.DuplicatedException;
import br.com.brunocoan.exception.NotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	
	public void createProduct(Product product) {
		verifyIfExists(product);
		repository.save(product);
	}

	
	public void deleteProduct(Long productId) {
		Product product = repository.findByProductId(productId);
		if(Objects.isNull(product)) {
			throw new NotFoundException("product not found"); 
		}
		repository.delete(product);
	}
	
	@Transactional
	public Collection<Product> listProduct(Long limit) {
		if(limit > 0) {
			Page<Product> page = repository.findAll(new PageRequest(0, limit.intValue()));
			return page.getContent();
		}
		
		return repository.findAll().as(ArrayList.class);
	}
	
	public Product findByProductId(Long productId) {
		Product product = repository.findByProductId(productId);
		if(Objects.isNull(product)) {
			throw new NotFoundException("product not found"); 
		}
		return product;
	}
	
	
	private void verifyIfExists(Product product) {
		Product result = repository.findByProductId(product.getProductId());
		if(Objects.nonNull(result)) {
			throw new DuplicatedException(String.format("product %s already created", product.getProductId()));
		}
	}
}
