package br.com.brunocoan.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
@NodeEntity
public class Product {
	@GraphId
	private Long nodeId;
	@Indexed(unique = true)
	private Long productId;
	private String name;
	private Double price;
	
	public Product() {	}
	public Product(Long productId, String name, Double price) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public Long getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "Product [nodeId=" + nodeId + ", productId=" + productId
				+ ", name=" + name + ", price=" + price + "]";
	}
	
}
