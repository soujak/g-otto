package g8.bookshop.business.core;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ShoppingCartLocal {
	/**
	 * Add an order in the shopping cart
	 * @param o order to add
	 */
	public boolean addOrder(Order o);
	
	/**
	 * Add a list of orders in the shopping cart
	 * @param l list to add
	 */
	public boolean addOrders(List<Order> l);

	public boolean update(List<Order> ords);

	public boolean checkOut();
}