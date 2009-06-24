package g8.bookshop.business.core;

import g8.bookshop.business.util.BeanLocator;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.Clustered;
import org.jboss.logging.Logger;

/**
 * Stateful Session Bean implementation class Customer
 * @author soujak
 */
@Clustered
@Stateful
public class Customer extends User implements CustomerRemote {

	private ShoppingCartRemote shoppingCart;

	/**
	 * @see CustomerRemote#getShoppingCart()
	 */
	public ShoppingCartRemote getShoppingCart() {
		System.out.println(this.getId() + ": getShoppingCart()");
		return shoppingCart;
	}

	/**
	 * Minimal constructor
	 */
	public Customer() {
		super();
		customer = true;
	}
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void createShoppingCart() {
		this.shoppingCart = null;
		try {
			this.shoppingCart = (ShoppingCartRemote) BeanLocator.getBean("BookshopBusinessCore/Customer/remote");
		} catch (NamingException e) {
			Logger logger = Logger.getLogger(Customer.class);
			logger.error(e.getStackTrace().toString());
		}
	}
}
