/**
 * 
 */
package g8.bookshop.business.core;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author soujak
 *
 */
public class ShoppingCart implements ShoppingCartLocal {

	private SortedMap<String,Order> orders;

	public ShoppingCart() {
		orders = Collections.synchronizedSortedMap(new TreeMap<String,Order>());
	}
	
	public void addOrder(Order o) {
		Order oldOrder = orders.get(o.getBook().getId()); 
		if (oldOrder != null)
			oldOrder.setQuantity(o.getQuantity()+oldOrder.getQuantity());
		else
			this.orders.put(o.getBook().getId(),o);
	}

}
