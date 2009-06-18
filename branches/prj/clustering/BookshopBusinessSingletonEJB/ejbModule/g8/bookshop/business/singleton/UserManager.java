package g8.bookshop.business.singleton;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.persistence.Credential;

import java.util.Collections;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.Service;

/**
 * HASingleton Service Bean implementation class UserManager
 * 
 * @author soujak
 */
@Service
// TODO setup its HASingletonController service
public class UserManager implements UserManagerRemote {

	private SortedMap<String, UserRemote> userMap;
	@PersistenceContext(unitName = "InformationManager")
	private EntityManager em;

	public UserManager() {
		super();
		userMap = Collections
				.synchronizedSortedMap(new TreeMap<String, UserRemote>());
	}

	/**
	 * Lookup for a user in the bookshop from his id
	 * 
	 * @param id
	 *            user id
	 * @return user to which the specified id is mapped, or null if id does not
	 *         exists
	 */
	public UserRemote lookup(String id) {
		return userMap.get(id);
	}

	/**
	 * Search a user in the bookshop from his id (possibly new)
	 * 
	 * @param id
	 *            user id
	 * @return User to which the specified id is mapped, if id is not mapped
	 *         create a new guest with the given id
	 * @throws NamingException 
	 */
	public UserRemote getUser(String id) throws NamingException {
		UserRemote u = userMap.get(id);
		if (u == null) {
			
			Properties env = new Properties();
			InitialContext ctx;
			env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			env.setProperty("jnp.partitionName", "G8Business");
			env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
			ctx = new InitialContext(env);
			u = (GuestRemote) ctx.lookup("BookshopBusinessCore/Guest/remote");
			u.setId(id);
			userMap.put(id, u);
		}
		return u;
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
	 * @throws NamingException 
	 */
	public boolean authenticate(GuestRemote g, String n, String p) throws NamingException {
		boolean ret;
		Credential cred = em.find(Credential.class, n);
		if (cred != null)
			if (cred.getPassword().equals(p)) {
				String id = g.getId();
				Properties env = new Properties();
				InitialContext ctx;
				env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
						"org.jnp.interfaces.NamingContextFactory");
				env.setProperty("jnp.partitionName", "G8Business");
				env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
				ctx = new InitialContext(env);
				CustomerRemote cust = (CustomerRemote) ctx.lookup("BookshopBusinessCore/Customer/remote");
				cust.setId(id);
				this.userMap.put(id, cust);
				ret = true;
			} else
				ret = false;
		else
			ret = false;
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
		return (this.userMap.remove(c.getId()) != null);
	}
}
