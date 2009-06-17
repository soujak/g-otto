package g8.bookshop.business.ws;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.singleton.UserManagerLocal;
import g8.bookshop.business.util.ConverterLocal;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jboss.ejb3.annotation.Clustered;
import org.xml.sax.SAXException;


/**
 * WebService Session Bean implementation class ShoppingCartService
 */
@Stateless
@Clustered
@WebService
public class ShoppingCartService implements ShoppingCartServiceRemote {
	
	@EJB
	private UserManagerLocal um;
	@EJB
	private ConverterLocal c;
	
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
		String ret = "";
		UserRemote u = um.lookup(id);
		if (u != null)
			if (u.isCustomer()) {
				try {
					ret = c.shoppingCartToXML(
							((CustomerRemote) u).getShoppingCart());
				} catch (IllegalArgumentException e) {} 
				catch (ParserConfigurationException e) {}
				catch (TransformerException e) {}
			}
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
		UserRemote u = um.lookup(id);
		if (u != null)
			if (u.isCustomer()) {
				try {
					ret = ((CustomerRemote) u).getShoppingCart()
					.addOrders(c.xmlToOrders(ords));
				} catch (IllegalArgumentException e) {} 
				catch (ParserConfigurationException e) {}
				catch (SAXException e) {}
				catch (IOException e) {}
			}
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
		UserRemote u = um.lookup(id);
		if (u != null)
			if (u.isCustomer()) {
				try {
					ret = ((CustomerRemote) u).getShoppingCart().update(c.xmlToOrders(ords));
				} catch (IllegalArgumentException e) {} 
				catch (ParserConfigurationException e) {}
				catch (SAXException e) {}
				catch (IOException e) {}
			}
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
		UserRemote u = um.lookup(id);
		if (u != null)
			if (u.isCustomer())
				ret = ((CustomerRemote) u).getShoppingCart().checkOut();
		return ret;
	}
}
