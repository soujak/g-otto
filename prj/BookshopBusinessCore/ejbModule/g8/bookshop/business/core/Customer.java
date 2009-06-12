package g8.bookshop.business.core;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

/**
 * Stateful Session Bean implementation class Customer
 * @author soujak
 */
@Stateful
public class Customer extends User implements CustomerLocal, CustomerRemote {

	private ShoppingCart shoppingCart;
	@Resource private SessionContext sessionContext;

	/**
	 * @see CustomerRemote#getShoppingCart()
	 */
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * Minimal constructor
	 */
	public Customer() {
		super();
		this.customer = true;
		this.shoppingCart =(ShoppingCart) this.sessionContext.lookup("BookshopBusiness/ShoppingCart/remote");
	}
}
