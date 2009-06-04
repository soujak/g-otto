package g8.bookshop.business.core;

import javax.ejb.Local;

@Local
public interface CustomerLocal {

	/**
	 * @return the shoppingCart
	 */
	public abstract ShoppingCartLocal getShoppingCart();

}