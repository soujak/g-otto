package g8.bookshop.business.ws;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.singleton.UserManagerRemote;
import g8.bookshop.business.util.ConverterRemote;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jboss.ejb3.annotation.Depends;
import org.xml.sax.SAXException;


/**
 * WebService Session Bean implementation class ShoppingCartService
 */
@Depends(value={"ear=BookshopBusinessCore.ear,jar=BookshopBusinessCoreEJB.jar,name=Converter,service=EJB3","ear=BookshopBusinessSingleton.ear,jar=BookshopBusinessSingletonEJB.jar,name=UserManager,service=EJB3"})
@Stateless
@WebService
public class ShoppingCartService implements ShoppingCartServiceRemote {
	
	private UserManagerRemote um;
	private ConverterRemote c;
	
	/**
	 * Default constructor
	 * @throws NamingException 
	 */
	public ShoppingCartService() throws NamingException {
		super();
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
		"org.jnp.interfaces.NamingContextFactory");
		env.setProperty("jnp.partitionName",
		"G8Business");
		env.setProperty(Context.URL_PKG_PREFIXES,
		"org.jboss.naming:org.jnp.interfaces");
		Context ctx = new InitialContext(env);
		this.um = (UserManagerRemote) ctx
			.lookup("BookshopBusinessSingleton/UserManager/remote");
		this.c = (ConverterRemote) ctx
		.lookup("BookshopBusinessCore/Converter/remote");
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
				catch (IOException e) {} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				catch (IOException e) {} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
