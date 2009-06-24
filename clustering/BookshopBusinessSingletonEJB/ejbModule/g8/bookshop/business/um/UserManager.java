/**
 * 
 */
package g8.bookshop.business.um;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.util.BeanLocator;
import g8.bookshop.persistence.Credential;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.jmx.adaptor.rmi.RMIAdaptor;
import org.jboss.logging.Logger;

/**
 * @author soujak
 *
 */
public class UserManager implements UserManagerMBean {

	private SortedMap<String, UserRemote> userMap;
	@PersistenceContext(unitName = "InformationManager")
	private EntityManager entityManager;
	private final Logger logger = Logger.getLogger(UserManager.class);
	private boolean isMasterNode;
	
	public UserManager() {
		super();
		this.userMap = Collections
			.synchronizedSortedMap(new TreeMap<String, UserRemote>());
		this.isMasterNode = false;
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
		boolean ret = false;
		if (!isMasterNode()) {
			ret = ((Boolean) this.reinvokeOnMasterNode(
					"authenticate",
					new Object[]{g,n,p},
					new String[]{"GuestRemote","String","String"})
			).booleanValue();
		} else {
			Credential cred = entityManager.find(Credential.class, n);
			if (cred != null) {
				if (cred.getPassword().equals(p)) {
					String id = g.getId();
					CustomerRemote customer;
					try {
						customer = (CustomerRemote)
						BeanLocator.getBean("BookshopBusinessCore/Customer/remote");
						customer.setId(id);
						this.userMap.put(id, customer);
						ret = true;
					} catch (NamingException e) {
						this.logger.error(e.getStackTrace());
					}
				}
			}
		}
		return ret;
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
		boolean ret = false;
		if (!isMasterNode()) {
			ret = ((Boolean) this.reinvokeOnMasterNode(
					"disconnect",
					new Object[]{c},
					new String[]{"CustomerRemote"})
			).booleanValue();
		} else {
			ret = this.userMap.remove(c.getId()) != null;
		}
		return ret;
	}
	/**
	 * Search a user in the bookshop from his id.
	 * If id is not mapped create a new guest with the given id.
	 * 
	 * @param id
	 *          user id
	 * @return
	 * 			user to which the specified id is mapped (possibly new)
	 * 			or null if some error occurred
	 */
	public UserRemote getUser(String id) {
		UserRemote user = null;
		if (!isMasterNode()) {
			user = ((UserRemote) this.reinvokeOnMasterNode(
					"getUser",
					new Object[]{id},
					new String[]{"String"}));
		} else {
			user = this.userMap.get(id);
			if (user == null) {
				try {
					user = (UserRemote) BeanLocator.getBean("BookshopBusinessCore/Guest/remote");
					user.setId(id);
					this.userMap.put(id, user);
				} catch (NamingException e) {
					this.logger.error(e.getStackTrace());
				}
			}
		}
		return user;
	}
	/**
	 * Lookup for a user in the bookshop from his id
	 * 
	 * @param id
	 *            user id
	 * @return user to which the specified id is mapped,
	 * 		null if id does not exists
	 */
	public UserRemote lookup(String id) {
		UserRemote user = null;
		if (!isMasterNode()) {
			user = ((UserRemote) this.reinvokeOnMasterNode(
					"lookup",
					new Object[]{id},
					new String[]{"String"}));
		} else {
			user = this.userMap.get(id);
		}
		return user;
	}
	
	public void create() throws Exception {
		this.logger.info("UserManager: create");
	}
	public void start() throws Exception {
		this.logger.info("UserManager: start");
	}
	public void stop(){
		this.logger.info("UserManager: stop");
	}
	public void destroy(){
		this.logger.info("UserManager: destroy");
	}

	public boolean isMasterNode() {
		return this.isMasterNode;
	}
	public void startSingleton() {
		this.isMasterNode=true;
		this.logger.info("UserManager: start singleton on node");
	}
	public void stopSingleton() {
		this.isMasterNode=false;
		this.logger.info("UserManager: stop singleton on node");
	}
	
	/**
	 * @return the master node address of the partition,
	 * 			null if something goes wrong
	 */
	@SuppressWarnings("unchecked")
	private String getMasterNode() {
		String masterNode = null;
		RMIAdaptor adaptor;
		try {
			adaptor = (RMIAdaptor) BeanLocator.getBean(
			"jmx/rmi/RMIAdaptor");
			//
			ObjectName name = new ObjectName("jboss:service=HAPartition,partition=G8Business");
			if (adaptor.isRegistered(name)) {
				masterNode = ((Vector) adaptor.getAttribute(name, "CurrentView")).get(0).toString();
			}
		} catch (MalformedObjectNameException e) {
			this.logger.error(e.getStackTrace().toString());
		} catch (AttributeNotFoundException e) {
			this.logger.error(e.getStackTrace().toString());
		} catch (InstanceNotFoundException e) {
			this.logger.error(e.getStackTrace().toString());
		} catch (MBeanException e) {
			this.logger.error(e.getStackTrace().toString());
		} catch (ReflectionException e) {
			this.logger.error(e.getStackTrace().toString());
		} catch (IOException e) {
			this.logger.error(e.getStackTrace().toString());
		} catch (NamingException e) {
			this.logger.error(e.getStackTrace());
		}
		return masterNode;
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
	private Object reinvokeOnMasterNode(String m, Object[] a, String[] t) {
		Object ret = null;
		String masterNode = this.getMasterNode();
		if (masterNode != null) {
			try {
				RMIAdaptor adaptor = (RMIAdaptor) BeanLocator.getBean(
					Context.PROVIDER_URL,
					masterNode,
					"jmx/rmi/RMIAdaptor");
				ObjectName name = new ObjectName(
				"g8.bookshop.business.um:service=UserManager");
				if (adaptor.isRegistered(name)) {
					this.logger.info(
						"UserManager: reinvoking on master node" + masterNode);
					ret = adaptor.invoke(name, m, a, t);
				}
			} catch (MalformedObjectNameException e) {
				this.logger.error(e.getStackTrace());
			} catch (InstanceNotFoundException e) {
				this.logger.error(e.getStackTrace());
			} catch (MBeanException e) {
				this.logger.error(e.getStackTrace());
			} catch (ReflectionException e) {
				this.logger.error(e.getStackTrace());
			} catch (IOException e) {
				this.logger.error(e.getStackTrace());
			} catch (NamingException e) {
				this.logger.error(e.getStackTrace());
			}
		}
		return ret;
	}
}
