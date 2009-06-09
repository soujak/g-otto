package g8.bookshop.business.core;

import javax.ejb.Stateful;

/**
 * Stateful Session Bean implementation class Customer
 * @author soujak
 */
@Stateful
public class Customer extends User implements CustomerLocal {

	private ShoppingCart shoppingCart;

	/**
	 * @see CustomerLocal#getShoppingCart()
	 */
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * Constructor
	 * @param id user id
	 */
	public Customer(String id) {
		super(id, true);
		shoppingCart = new ShoppingCart();
	}
}
