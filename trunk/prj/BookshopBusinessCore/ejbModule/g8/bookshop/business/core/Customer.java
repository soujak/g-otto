/**
 * 
 */
package g8.bookshop.business.core;

/**
 * @author soujak
 *
 */
public class Customer extends User {

	private ShoppingCart shoppingCart;

	/**
	 * @return the shoppingCart
	 */
	public ShoppingCart getShoppingCart() {
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
