package br.com.brunocoan.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunocoan.domain.Action;
import br.com.brunocoan.domain.AddedCart;
import br.com.brunocoan.domain.Bought;
import br.com.brunocoan.domain.Person;
import br.com.brunocoan.domain.Viewed;
import br.com.brunocoan.repository.ActionRepository;

@Service
public class ActionService {
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private PersonService personService;
	
	public void createViewedAction(Long personId, Long productId) {
		Viewed viewed = new Viewed();
		viewed.setDate(new Date());
		viewed.setPerson(personService.findByPersonId(personId));
		viewed.setProduct(productService.findByProductId(productId));
		
		actionRepository.save(viewed);
	}
	
	public void createAddedCartAction(Long personId, Long productId) {
		AddedCart addedCart = new AddedCart();
		addedCart.setDate(new Date());
		addedCart.setPerson(personService.findByPersonId(personId));
		addedCart.setProduct(productService.findByProductId(productId));
		
		actionRepository.save(addedCart);
	}
	
	public void createBoughtAction(Long personId, Long productId) {
		Bought bought = new Bought();
		bought.setDate(new Date());
		bought.setPerson(personService.findByPersonId(personId));
		bought.setProduct(productService.findByProductId(productId));
		
		actionRepository.save(bought);
	}

	public Collection<Action> actionsByPerson(Long personId, Long limit) {
		if(limit > 0) {
			return actionRepository.findByPerson(personService.findByPersonId(personId), limit);
		}
		return actionRepository.findByPerson(personService.findByPersonId(personId));

	}

}
