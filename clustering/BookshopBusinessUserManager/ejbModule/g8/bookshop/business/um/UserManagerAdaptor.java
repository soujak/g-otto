package g8.bookshop.business.um;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.util.BeanLocator;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
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
	 */
	public UserRemote lookup(String id) {
		return (UserRemote) this.invokeUserManager(
				"lookup",
				new Object[]{id},
				new String[]{"java.lang.String"});
	}

	/**
	 * Search a user in the bookshop from his id (possibly new)
	 * 
	 * @param id
	 *            user id
	 * @return user to which the specified id is mapped (possibly new)
	 * 			or null if some error occurred
	 */
	public UserRemote getUser(String id) {
		Logger logger = Logger.getLogger(UserManagerAdaptor.class);
		logger.info("getUser: id "+id);
		// FIXME
		return (UserRemote) this.invokeUserManager(
				"getUser", new Object[]{id}, new String[]{"java.lang.String"});
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
		Logger logger = Logger.getLogger(UserManagerAdaptor.class);
		logger.info("authenticate: name "+n+", password "+p);
		return (Boolean) this.invokeUserManager(
				"authenticate",
				new Object[]{g,n,p},
				new String[]{"g8.bookshop.business.core.GuestRemote","java.lang.String","java.lang.String"});
	}

	/**
	 * Disconnect a given customer from the bookshop
	 * 
	 * @param c
	 *            Customer to disconnect
	 * @return true if the customer is successfully disconnected, false
	 *         otherwise
	 */
	public boolean disconnect(CustomerRemote c)  {
		return (Boolean) this.invokeUserManager(
				"disconnect",
				new Object[]{c},
				new String[]{"g8.bookshop.business.core.CustomerRemote"});
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
	 * @throws ReflectionException 
	 */
	private Object invokeUserManager(String m, Object[] a, String[] t) {
		Logger logger = Logger.getLogger(UserManagerAdaptor.class);
		logger.info("invoke "+m+" on user manager");
		Object ret = null;
		try {
			RMIAdaptor adaptor = (RMIAdaptor) BeanLocator.getBean(
			"jmx/invoker/RMIAdaptor");
			ObjectName name = new ObjectName(
			"g8.bookshop.business.um:service=UserManager");
			if (adaptor.isRegistered(name)) {
				ret = adaptor.invoke(name, m, a, t);
			}
		} catch (MalformedObjectNameException e) {
			logger.fatal(e);
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			logger.fatal(e);
			e.printStackTrace();
		} catch (MBeanException e) {
			logger.fatal(e);
			e.printStackTrace();
		} catch (ReflectionException e) {
			logger.fatal(e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.fatal(e);
			e.printStackTrace();
		} catch (NamingException e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return ret;
	}
}
