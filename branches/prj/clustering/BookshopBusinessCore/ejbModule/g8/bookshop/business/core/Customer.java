package g8.bookshop.business.core;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

/**
 * Stateful Session Bean implementation class Customer
 * @author soujak
 */
@Stateful
public class Customer extends User implements CustomerLocal, CustomerRemote {

	private ShoppingCartRemote shoppingCart;
	@Resource 
	private SessionContext sessionContext;

	/**
	 * @see CustomerRemote#getShoppingCart()
	 */
	public ShoppingCartRemote getShoppingCart() {
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
		shoppingCart = (ShoppingCartRemote)sessionContext.lookup("BookshopBusiness/ShoppingCart/remote");
	}
}
