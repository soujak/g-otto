package g8.bookshop.business.ws;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.um.UserManager;
import g8.bookshop.business.um.UserManagerAdaptorRemote;
import g8.bookshop.business.util.BeanLocator;
import g8.bookshop.business.util.ConverterRemote;
import g8.bookshop.business.util.Name;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jboss.logging.Logger;
import org.xml.sax.SAXException;


/**
 * WebService Session Bean implementation class ShoppingCartService
 */
@Stateless
@WebService
public class ShoppingCartService implements ShoppingCartServiceRemote {
	
	/**
	 * Default constructor
	 * @throws NamingException 
	 */
	public ShoppingCartService() throws NamingException {
		super();
	}

	/**
	 * View the shopping cart
	 * @param id User id
	 * @return Shopping cart in XML format
	 */
	@WebMethod
	public String view(String id) {
		Logger logger = Logger.getLogger(UserManagerService.class);
		logger.info("view: id "+id);
		UserManagerAdaptorRemote userManagerAdaptor;
		ConverterRemote converter;
		String ret = "";

		try {
			userManagerAdaptor =
				(UserManagerAdaptorRemote) BeanLocator.getBean(
						Name.EJB.USERMANAGER_ADAPTOR_REMOTE);
			converter =
				(ConverterRemote) BeanLocator.getBean(
						Name.EJB.CONVERTER_REMOTE);
			UserRemote u = userManagerAdaptor.lookup(id);
			if (u != null)
				if (u.isCustomer()) {
					ret = converter.shoppingCartToXML(
							((CustomerRemote) u).getShoppingCart());
				}
			}
		catch (IllegalArgumentException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		} 
		catch (ParserConfigurationException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();					
		}
		catch (TransformerException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();	
		}
		catch (NamingException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
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
		Logger.getLogger(UserManagerService.class).info("addOrders "+id+" "+ords);
		UserManagerAdaptorRemote userManagerAdaptor;
		ConverterRemote converter;
		boolean ret = false;
		try {
			userManagerAdaptor =
				(UserManagerAdaptorRemote) BeanLocator.getBean(
						Name.EJB.USERMANAGER_ADAPTOR_REMOTE);
			converter =
				(ConverterRemote) BeanLocator.getBean(
						Name.EJB.CONVERTER_REMOTE);
			UserRemote u = userManagerAdaptor.lookup(id);
			if (u != null)
				if (u.isCustomer()) {
					ret = ((CustomerRemote) u).getShoppingCart()
					.addOrders(converter.xmlToOrders(ords));
				}
		}
		catch (IllegalArgumentException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		} 
		catch (ParserConfigurationException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		}
		catch (SAXException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		}
		catch (IOException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		} catch (NamingException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
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
		Logger.getLogger(UserManagerService.class).info("update "+id+" "+ords);
		UserManagerAdaptorRemote userManagerAdaptor;
		ConverterRemote converter;
		boolean ret = false;
		try {
			userManagerAdaptor =
				(UserManagerAdaptorRemote) BeanLocator.getBean(
						Name.EJB.USERMANAGER_ADAPTOR_REMOTE);
			converter =
				(ConverterRemote) BeanLocator.getBean(
						Name.EJB.CONVERTER_REMOTE);
			UserRemote u = userManagerAdaptor.lookup(id);
			if (u != null)
				if (u.isCustomer())
					ret = ((CustomerRemote) u).getShoppingCart().update(
							converter.xmlToOrders(ords));
		} catch (IllegalArgumentException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		} catch (SAXException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		} catch (IOException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		} catch (NamingException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
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
		Logger.getLogger(UserManagerService.class).info("update "+id);
		UserManagerAdaptorRemote userManagerAdaptor;
		boolean ret = false;
		try {
			userManagerAdaptor =
				(UserManagerAdaptorRemote) BeanLocator.getBean(
						Name.EJB.USERMANAGER_ADAPTOR_REMOTE);
			UserRemote u = userManagerAdaptor.lookup(id);
			if (u != null)
				if (u.isCustomer())
					ret = ((CustomerRemote) u).getShoppingCart().checkOut();
		} catch (NamingException e) {
			Logger.getLogger(UserManager.class).fatal(e);
			e.printStackTrace();
		}
		return ret;
	}
}
