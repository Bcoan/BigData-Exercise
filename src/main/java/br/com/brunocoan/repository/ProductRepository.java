package br.com.brunocoan.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import br.com.brunocoan.domain.Product;
@Repository
public interface ProductRepository extends GraphRepository<Product> {
	
	public Product findByProductId(Long productId);

}
