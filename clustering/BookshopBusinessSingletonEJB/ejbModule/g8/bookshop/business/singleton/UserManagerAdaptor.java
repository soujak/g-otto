package g8.bookshop.business.singleton;

import java.io.IOException;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.um.UserManager;
import g8.bookshop.business.util.BeanLocator;

import javax.ejb.Stateless;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.Clustered;
import org.jboss.jmx.adaptor.rmi.RMIAdaptor;
import org.jboss.logging.Logger;

/**
 * TODO
 * HASingleton Service Bean implementation class UserManagerAdaptor
 * 
 * @author soujak
 */
@Clustered
@Stateless
public class UserManagerAdaptor implements UserManagerAdaptorRemote {

	/**
	 * Lookup for a user in the bookshop from his id
	 * 
	 * @param id
	 *            user id
	 * @return user to which the specified id is mapped, or null if id does not
	 *         exists
	 */
	public UserRemote lookup(String id) {
		return (UserRemote) this.invokeUserManager(
				"lookup",
				new Object[]{id},
				new String[]{"String"});
	}

	/**
	 * Search a user in the bookshop from his id (possibly new)
	 * 
	 * @param id
	 *            user id
	 * @return User to which the specified id is mapped, if id is not mapped
	 *         create a new guest with the given id
	 */
	public UserRemote getUser(String id) {
		return (UserRemote) this.invokeUserManager(
				"getUser",
				new Object[]{id},
				new String[]{"String"});
	}

	/**
	 * Authenticate a customer in the bookshop, verifying his credentials
	 * 
	 * @param g
	 *            Guest to authenticate
	 * @param n
	 *            Guest's name
	 * @param p
	 *            Guest's password
	 * @return true if the guest is successfully authenticated, false otherwise
	 */
	public boolean authenticate(GuestRemote g, String n, String p) {
		return (Boolean) this.invokeUserManager(
				"authenticate",
				new Object[]{g,n,p},
				new String[]{"GuestRemote","String","String"});
	}

	/**
	 * Disconnect a given customer from the bookshop
	 * 
	 * @param c
	 *            Customer to disconnect
	 * @return true if the customer is successfully disconnected, false
	 *         otherwise
	 */
	public boolean disconnect(CustomerRemote c) {
		return (Boolean) this.invokeUserManager(
				"disconnect",
				new Object[]{c},
				new String[]{"CustomerRemote"});
	}
	
	/**
	 * Re-invoke a method on the active user manager (HASingleton) 
	 * @param m
	 * 			name of the method to invoke
	 * @param a
	 * 			arguments of the method
	 * @param t
	 * 			types of arguments
	 * @return
	 * 			the return value of the invocation
	 */
	private Object invokeUserManager(String m, Object[] a, String[] t) {
		Object ret = null;
		Logger logger = Logger.getLogger(UserManagerAdaptor.class);
		try {
			RMIAdaptor adaptor = (RMIAdaptor) BeanLocator.getBean(
			"jmx/rmi/RMIAdaptor");
			ObjectName name = new ObjectName(
			"g8.bookshop.business.um:service=UserManager");
			if (adaptor.isRegistered(name)) {
				ret = adaptor.invoke(name, m, a, t);
			}
		} catch (MalformedObjectNameException e) {
			logger.error(e.getStackTrace());
		} catch (InstanceNotFoundException e) {
			logger.error(e.getStackTrace());
		} catch (MBeanException e) {
			logger.error(e.getStackTrace());
		} catch (ReflectionException e) {
			logger.error(e.getStackTrace());
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		} catch (NamingException e) {
			logger.error(e.getStackTrace());
		}
		return ret;
	}
}
