/**
 * 
 */
package g8.bookshop.business.core;

/**
 * @author soujak
 *
 */
public class Customer extends User implements CustomerLocal {

	private ShoppingCartLocal shoppingCart;

	/* (non-Javadoc)
	 * @see g8.bookshop.business.core.CustomerLocal#getShoppingCart()
	 */
	public ShoppingCartLocal getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * @param id user id
	 */
	public Customer(String id) {
		super(id, true);
		shoppingCart = new ShoppingCart();
	}
}
