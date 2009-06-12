/**
 * 
 */
package g8.bookshop.business.core;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.ejb.Stateful;

/**
 * Implementation class ShoppingCart
 * @author soujak
 */
@Stateful
public class ShoppingCart implements ShoppingCartLocal, ShoppingCartRemote {

	private SortedMap<Long,Order> orders;

	/**
	 * Constructor
	 */
	public ShoppingCart() {
		this.orders = Collections.synchronizedSortedMap(new TreeMap<Long,Order>());
	}
	
	/**
	 * Add an order in the shopping cart
	 * @param o order to add
	 */
	public boolean addOrder(Order o) {
		Order oldOrder = orders.get(o.getBook().getId()); 
		if (oldOrder != null)
			oldOrder.setQuantity(o.getQuantity()+oldOrder.getQuantity());
		else
			this.orders.put(Long.valueOf(o.getBook().getId()),o);
		// FIXME add an order insertion/update check?
		return true;
	}
	
	/**
	 * Add a list of orders in the shopping cart
	 * @param l list to add
	 */
	public boolean addOrders(List<Order> l) {
		boolean ret = true;
		for (ListIterator<Order> i = l.listIterator(); i.hasNext();) {
			Order o = i.next();
			// TODO check validity of the short hand version:
			// ret &= addOrder(o);
			ret = ret && addOrder(o);
		}
		return ret;
	}

	/**
	 * Update the shopping cart with the given orders
	 * @param ords Orders
	 * @return true if the shopping cart is successfully updated, false otherwise
	 */
	public boolean update(List<Order> ords) {
		this.orders = Collections.synchronizedSortedMap(new TreeMap<Long,Order>());
		return this.addOrders(ords);
	}
	
	/**
	 * Check out the shopping cart and empty it
	 * @return true
	 */
	public boolean checkOut() {
		// TODO check: clear could be unimplemented
		this.orders.clear();
		return true;
	}
}
