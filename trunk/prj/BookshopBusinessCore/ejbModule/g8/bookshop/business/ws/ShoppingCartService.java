package g8.bookshop.business.ws;

import javax.ejb.EJB;

import org.jboss.beans.metadata.api.annotations.Inject;

import g8.bookshop.business.core.Customer;
import g8.bookshop.business.core.CustomerLocal;
import g8.bookshop.business.core.ShoppingCartLocal;
import g8.bookshop.business.core.UserLocal;
import g8.bookshop.business.core.UserManagerLocal;
import g8.bookshop.business.util.Converter;


/**
 * WebService Session Bean implementation class ShoppingCartService
 */
public class ShoppingCartService implements ShoppingCartServiceRemote {
	
	@EJB private UserManagerLocal um;
	
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
	public String View(String id) {
		UserLocal u = um.lookup(id);
		String ret = null;
		if (u != null)
			if (u.isCustomer())
				ret = Converter.toXML(((Customer) u).getShoppingCart());
		return ret;
	}
	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return
	 */
	public boolean AddOrders(String id, String ords) {
		 // TODO
//		orders=ords.toOrder;
//		return um.getCustomer(id).getShoppingCart().addOrders(orders);
		return false;
	}
	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return 
	 */
	public boolean Update(String id, String ords) {
		 // TODO
		return false;
	}
	/**
	 * @param id User id
	 * @return
	 */
	public boolean Checkout(String id) {
		 // TODO
		return false;
	}
}
