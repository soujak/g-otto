package g8.bookshop.business.core;

import javax.ejb.Remote;

@Remote
public interface CustomerRemote extends UserRemote {

	/**
	 * @return the shoppingCart
	 */
	public abstract ShoppingCart getShoppingCart();
}