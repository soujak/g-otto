package g8.bookshop.business.core;

import javax.ejb.Remote;

/**
 * TODO javadoc
 */
@Remote
public interface CustomerRemote extends UserRemote {

	/**
	 * TODO javadoc
	 */
	/**
	 * @return the shoppingCart
	 */
	public abstract ShoppingCartRemote getShoppingCart();
}