package g8.bookshop.business.core;

import javax.ejb.Local;

@Local
public interface ShoppingCartLocal {
	/**
	 * Add an order in the shopping cart
	 * @param o order to add
	 */
	public void addOrder(Order o);
}