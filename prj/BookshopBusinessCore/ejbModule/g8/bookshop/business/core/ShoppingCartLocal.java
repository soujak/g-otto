package g8.bookshop.business.core;

import java.util.List;

import javax.ejb.Local;

/**
 * Local interface ShoppingCart
 * @author soujak
 */
@Local
public interface ShoppingCartLocal {
	/**
	 * Add an order in the shopping cart
	 * @param o order to add
	 */
	public boolean addOrder(OrderRemote o);
	
	/**
	 * Add a list of orders in the shopping cart
	 * @param l list to add
	 */
	public boolean addOrders(List<OrderRemote> l);

	/**
	 * Update the shopping cart with the given orders
	 * @param ords Orders
	 * @return true if the shopping cart is successfully updated, false otherwise
	 */
	public boolean update(List<OrderRemote> ords);
	
	/**
	 * Check out the shopping cart and empty it
	 * @return true
	 */
	public boolean checkOut();
	
	/**
	 * Return the orders
	 * @return
	 */
	public List<OrderRemote> getOrders();
}