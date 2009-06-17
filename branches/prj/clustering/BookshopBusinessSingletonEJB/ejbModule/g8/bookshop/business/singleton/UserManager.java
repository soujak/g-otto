package g8.bookshop.business.singleton;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.persistence.Credential;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.Service;

/**
 * HASingleton Service Bean implementation class UserManager
 * 
 * @author soujak
 */
@Service
// TODO set as a HASingleton
// TODO setup its HASingletonController service
public class UserManager implements UserManagerRemote {

	private SortedMap<String, UserRemote> userMap;
	@Resource
	private SessionContext sessionContext;
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
	 */
	public UserRemote getUser(String id) {
		UserRemote u = userMap.get(id);
		if (u == null) {
			u = (GuestRemote) this.sessionContext
					.lookup("BookshopBusinessCore/Guest/remote");
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
	 */
	public boolean authenticate(GuestRemote g, String n, String p) {
		boolean ret;
		Credential cred = em.find(Credential.class, n);
		if (cred != null)
			if (cred.getPassword().equals(p)) {
				String id = g.getId();
				CustomerRemote cust = (CustomerRemote) this.sessionContext.lookup("BookshopBusinessCore/Customer/remote");
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
