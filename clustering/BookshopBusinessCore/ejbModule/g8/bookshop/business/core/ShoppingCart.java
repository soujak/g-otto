/**
 * 
 */
package g8.bookshop.business.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.Clustered;

/**
 * Implementation class ShoppingCart
 * @author soujak
 */
@Clustered
@Stateful
public class ShoppingCart implements ShoppingCartRemote {

	private SortedMap<Long,OrderRemote> orders;

	/**
	 * Constructor
	 */
	public ShoppingCart() {
		this.orders = Collections.synchronizedSortedMap(new TreeMap<Long,OrderRemote>());
	}
	
	/**
	 * Add an order in the shopping cart
	 * @param o order to add
	 */
	public boolean addOrder(OrderRemote o) {
		OrderRemote oldOrder = orders.get(o.getBook().getId()); 
		if (oldOrder != null)
			oldOrder.setQuantity(o.getQuantity()+oldOrder.getQuantity());
		else
			this.orders.put(Long.valueOf(o.getBook().getId()),o);
		return true;
	}
	
	/**
	 * Add a list of orders in the shopping cart
	 * @param l list to add
	 */
	public boolean addOrders(List<OrderRemote> l) {
		boolean ret = true;
		for (ListIterator<OrderRemote> i = l.listIterator(); i.hasNext();) {
			OrderRemote o = i.next();
			ret = ret && addOrder(o);
		}
		return ret;
	}

	/**
	 * Update the shopping cart with the given orders and
	 * remove zero-quantity orders
	 * @param ords Orders to put in the shopping cart
	 * @return true if the shopping cart is successfully updated,
	 * false otherwise
	 */
	public boolean update(List<OrderRemote> ords) {
		boolean ok = true;
		SortedMap<Long, OrderRemote> newOrders = Collections
				.synchronizedSortedMap(new TreeMap<Long, OrderRemote>());
		for (Iterator<OrderRemote> iterator = ords.iterator();
				iterator.hasNext() && ok;) {
			OrderRemote ord = iterator.next();
			if (ord.getQuantity() > 0) {
				newOrders.put(ord.getBook().getId(), ord);
				ok = true;
			} else if (ord.getQuantity() < 0)
				ok = false;
		}
		if (ok)
			this.orders = newOrders;
		return ok;
	}
	
	/**
	 * Check out the shopping cart and empty it
	 * @return true
	 */
	public boolean checkOut() {
		this.orders.clear();
		return true;
	}
	
	/**
	 * View shopping cart content
	 * @return the orders of the shopping cart
	 */
	public List<OrderRemote> getOrders() {
		return new ArrayList<OrderRemote>(this.orders.values());
	}
}
