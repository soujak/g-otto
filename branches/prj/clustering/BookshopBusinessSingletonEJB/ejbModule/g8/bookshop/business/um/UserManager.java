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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.jmx.adaptor.rmi.RMIAdaptor;
import org.jboss.logging.Logger;

/**
 * @author soujak
 *
 */
public class UserManager implements UserManagerMBean {

	private SortedMap<String, UserRemote> userMap;
//	@PersistenceContext(unitName = "InformationManager")
	EntityManagerFactory entityManagerFact;
	private EntityManager entityManager;
	private final Logger logger;
	private boolean isMasterNode;
	
	public UserManager() {
		super();
		this.isMasterNode = false;
		this.entityManagerFact = Persistence.createEntityManagerFactory("InformationManager");
		this.entityManager = entityManagerFact.createEntityManager();
		logger = Logger.getLogger(UserManager.class);
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
		logger.info("authenticate: id "+g.getId()+", user "+n);
		boolean ret = false;
		if (!this.isMasterNode()) {
			this.logger.info("authenticate: not master node, reinvoking");
			ret = ((Boolean) this.reinvokeOnMasterNode(
					"authenticate",
					new Object[]{g,n,p},
					new String[]{"g8.bookshop.business.GuestRemote",
							"java.lang.String","java.lang.String"})
			).booleanValue();
		} else {
			this.logger.info("authenticate: master node, executing");
			Credential cred;
			try {
				this.logger.info("authenticate: searching the password for " + n);
				cred = this.entityManager.find(Credential.class, n);
			} catch(Exception e) {
				this.logger.fatal("authenticate: entity access failed because of "+e);
				e.printStackTrace();
				return false;
			}
			if (cred != null) {
				this.logger.info("found user "+cred.getName());
				if (cred.getPassword().equals(p)) {
					String id = g.getId();
					CustomerRemote customer;
					try {
						Logger logger = Logger.getLogger(BeanLocator.class);
						customer = (CustomerRemote)BeanLocator.getBean("BookshopBusinessCore/Customer/remote");
						customer.setId(id);
						this.userMap.put(id, customer);
						logger.info("authenticate: access granted for user "+n);
						ret = true;
					} catch (NamingException e) {
						this.logger.fatal(e);
						e.printStackTrace();
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
		logger.info("disconnect: id "+c.getId());
		boolean ret = false;
		if (!isMasterNode()) {
			ret = ((Boolean) this.reinvokeOnMasterNode(
					"disconnect",
					new Object[]{c},
					new String[]{"g8.bookshop.business.CustomerRemote"})
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
		this.logger.info("get user "+ id);
		UserRemote user = null;
		if (!isMasterNode()) {
			user = ((UserRemote) this.reinvokeOnMasterNode(
					"getUser",
					new Object[]{id},
					new String[]{"java.lang.String"}));
		} else {
			user = this.userMap.get(id);
			if (user == null) {
				try {
					user = (GuestRemote) BeanLocator.getBean("BookshopBusinessCore/Guest/remote");
					user.setId(id);
					this.userMap.put(id, user);
					this.logger.info("get user: created new guest");
				} catch (NamingException e) {
					this.logger.fatal(e);
					e.printStackTrace();
				}
			} else {
				this.logger.info("get user: existing user");
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
		this.logger.info("lookup "+ id);
		UserRemote user = null;
		if (!isMasterNode()) {
			user = ((UserRemote) this.reinvokeOnMasterNode(
					"lookup",
					new Object[]{id},
					new String[]{"java.lang.String"}));
		} else {
			user = this.userMap.get(id);
		}
		return user;
	}
	
	public void create() throws Exception {
		this.logger.info("create");
	}
	public void start() throws Exception {
		this.logger.info("start");
		
	}
	public void stop(){
		this.logger.info("stop");
	}
	public void destroy(){
		this.logger.info("destroy");
		this.entityManagerFact.close();
	}

	public boolean isMasterNode() {
		return this.isMasterNode;
	}
	public void startSingleton() {
		this.logger.info("singleton started");
		this.isMasterNode=true;
		this.userMap = Collections
			.synchronizedSortedMap(new TreeMap<String, UserRemote>());
	}
	public void stopSingleton() {
		this.logger.info("singleton stopped");
		this.isMasterNode=false;
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
			ObjectName name = new ObjectName(
					"jboss:service=HAPartition,partition=G8Business");
			if (adaptor.isRegistered(name)) {
				masterNode = ((Vector) adaptor.getAttribute(name, "CurrentView")).get(0).toString();
			}
		} catch (MalformedObjectNameException e) {
			this.logger.fatal(e);
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			this.logger.fatal(e);
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			this.logger.fatal(e);
			e.printStackTrace();
		} catch (MBeanException e) {
			this.logger.fatal(e);
			e.printStackTrace();
		} catch (ReflectionException e) {
			this.logger.fatal(e);
			e.printStackTrace();
		} catch (IOException e) {
			this.logger.fatal(e);
			e.printStackTrace();
		} catch (NamingException e) {
			this.logger.fatal(e);
			e.printStackTrace();
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
		this.logger.info("reinvoke "+m+" on master node "+masterNode);
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
				this.logger.fatal(e);
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				this.logger.fatal(e);
				e.printStackTrace();
			} catch (MBeanException e) {
				this.logger.fatal(e);
				e.printStackTrace();
			} catch (ReflectionException e) {
				this.logger.fatal(e);
				e.printStackTrace();
			} catch (IOException e) {
				this.logger.fatal(e);
				e.printStackTrace();
			} catch (NamingException e) {
				this.logger.fatal(e);
				e.printStackTrace();
			}
		}
		return ret;
	}
}
