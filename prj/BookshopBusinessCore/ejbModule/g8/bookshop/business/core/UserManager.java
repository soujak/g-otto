package g8.bookshop.business.core;

import g8.bookshop.persistence.Credential;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.ejb3.annotation.Service;

/**
 * HASingleton Service Bean implementation class UserManager
 * @author soujak
 */
@Service
// TODO set as a HASingleton
// TODO setup its HASingletonController service
public class UserManager implements UserManagerLocal {

	private SortedMap<String,User> userMap;
	@PersistenceUnit(unitName="InformationManager")
	private EntityManagerFactory entManFactory; 
	
	public UserManager() {
		super();
		userMap = Collections.synchronizedSortedMap(new TreeMap<String,User>());
	}
	
	/**
	 * Lookup for a user in the bookshop from his id
	 * @param id user id
	 * @return user to which the specified id is mapped, or null if id does not exists 
	 */
	public UserLocal lookup(String id) {
		return userMap.get(id);
	}
	
	/**
	 * Search a user in the bookshop from his id (possibly new) 
	 * @param id user id.
	 * @return User to which the specified id is mapped,
	 * 		if id is not mapped create a new guest with the given id. 
	 */
	public UserLocal getUser(String id) {
		User u = userMap.get(id);
		if (u == null) {
			u = new Guest(id);
			userMap.put(id, u);
		}
		return u;
	}
	
	/**
	 * Authenticate a customer in the bookshop, verifying his credentials 
	 * @param g Guest to authenticate
	 * @param n Guest's name
	 * @param p Guest's password
	 * @return true if the guest is successfully authenticated, false otherwise
	 */
	public boolean authenticate(GuestLocal g, String n, String p) {
		boolean ret;
		EntityManager em = this.entManFactory.createEntityManager();
		Credential c = em.find(Credential.class, n);
		if (c != null)
			if (c.getPassword().equals(p)) {
				String id = g.getId(); 
				this.userMap.put(id, new Customer(id));
				ret = true;
			}
			else
				ret = false;
		else
			ret = false;
		return ret;
	}

	/**
	 * Disconnect a given customer from the bookshop
	 * @param c Customer to disconnect
	 * @return true if the customer is successfully disconnected, false otherwise
	 */
	public boolean disconnect(CustomerLocal c) {
		return (this.userMap.remove(c.getId()) != null);
	}
}
