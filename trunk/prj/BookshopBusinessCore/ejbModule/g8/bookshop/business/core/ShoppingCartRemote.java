package g8.bookshop.business.core;

import java.util.List;

import javax.ejb.Remote;

/**
 * Remote interface ShoppingCart
 * @author soujak
 */
@Remote
public interface ShoppingCartRemote {
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

	/**
	 * Update the shopping cart with the given orders
	 * @param ords Orders
	 * @return true if the shopping cart is successfully updated, false otherwise
	 */
	public boolean update(List<Order> ords);
	
	/**
	 * Check out the shopping cart and empty it
	 * @return true
	 */
	public boolean checkOut();
	
	/**
	 * Return the orders
	 * @return
	 */
	public List<Order> getOrders();
}