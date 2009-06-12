package g8.bookshop.business.ws;

import g8.bookshop.business.core.Customer;
import g8.bookshop.business.core.User;
import g8.bookshop.business.core.UserManager;
import g8.bookshop.business.util.Converter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * WebService Session Bean implementation class ShoppingCartService
 */
@Stateless
@WebService
public class ShoppingCartService implements ShoppingCartServiceRemote {
	
	@EJB
	private UserManager um;
	
	/**
	 * Default constructor
	 */
	public ShoppingCartService() {
		super();
	}

	/**
	 * View the shopping cart
	 * @param id User id
	 * @return Shopping cart in XML format
	 */
	@WebMethod
	public String view(String id) {
		String ret = null;
		User u = um.lookup(id);
		if (u != null)
			if (u.isCustomer())
				ret = Converter.toXML(((Customer) u).getShoppingCart());
		return ret;
	}
	
	/**
	 * Add orders to the shopping cart
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return true if the orders is successfully added, false otherwise
	 */
	@WebMethod
	public boolean addOrders(String id, String ords) {
		boolean ret = false;
		User u = um.lookup(id);
		if (u != null)
			if (u.isCustomer())
				ret = ((Customer) u).getShoppingCart().addOrders(Converter.toOrders(ords));
		return ret;
	}
	
	/**
	 * Update the shopping cart with the given orders
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return true if the shopping cart is successfully updated, false otherwise
	 */
	@WebMethod
	public boolean update(String id, String ords) {
		 // TODO
		boolean ret = false;
		User u = um.lookup(id);
		if (u != null)
			if (u.isCustomer())
				ret = ((Customer) u).getShoppingCart().update(Converter.toOrders(ords));
		return ret;
	}
	
	/**
	 * Check out the shopping cart with the given orders
	 * @param id User id
	 * @return true if the shopping cart is successfully checked out, false otherwise
	 */
	@WebMethod
	public boolean checkOut(String id) {
		 // TODO
		boolean ret = false;
		User u = um.lookup(id);
		if (u != null)
			if (u.isCustomer())
				ret = ((Customer) u).getShoppingCart().checkOut();
		return ret;
	}
}
