package g8.bookshop.business.core;

import javax.ejb.Local;

@Local
public interface CustomerLocal extends UserLocal {

	/**
	 * @return the shoppingCart
	 */
	public abstract ShoppingCart getShoppingCart();
}