package g8.bookshop.business.ws;

import javax.ejb.Remote;

/**
 * TODO javadoc
 * WebService Session Bean remote interface ShoppingCartServiceRemote
 */
@Remote
public interface ShoppingCartServiceRemote {

	/**
	 * View the shopping cart
	 * @param id User id
	 * @return Shopping cart in XML format
	 */
	String view(String id);

	/**
	 * Add orders to the shopping cart
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return true if the orders is successfully added, false otherwise
	 */
	boolean addOrders(String id, String ords);

	/**
	 * Update the shopping cart with the given orders
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return true if the shopping cart is successfully updated, false otherwise
	 */
	boolean update(String id, String ords);
	
	/**
	 * Check out the shopping cart with the given orders
	 * @param id User id
	 * @return true if the shopping cart is successfully checked out, false otherwise
	 */
	boolean checkOut(String id);
}