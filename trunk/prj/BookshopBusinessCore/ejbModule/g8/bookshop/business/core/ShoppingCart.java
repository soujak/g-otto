/**
 * 
 */
package g8.bookshop.business.core;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author soujak
 *
 */
public class ShoppingCart implements ShoppingCartLocal {

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
		boolean ret = false;
		for (Iterator<Order> i = l.listIterator(); i.hasNext();) {
			Order o = i.next();
			// TODO check validity of the short hand version:
			// ret &= addOrder(o);
			ret = ret && addOrder(o);
		}
		return ret;
	}

	public boolean update(List<Order> ords) {
		this.orders = Collections.synchronizedSortedMap(new TreeMap<Long,Order>());
		return this.addOrders(ords);
	}

	public boolean checkOut() {
		// TODO
		// I am dummy! Huh?
		return true;
	}
}
