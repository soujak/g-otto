package g8.bookshop.business.core;

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
	@PersistenceUnit(unitName="")
	private EntityManagerFactory entManFactory; 
	
	
	public UserManager() {
		userMap = Collections.synchronizedSortedMap(new TreeMap<String,User>());
		// TODO
	}
	
	/**
	 * @param id user id
	 * @return user to which the specified id is mapped, or null if id does not exists 
	 */
	public UserLocal lookup(String id) {
		return userMap.get(id);
	}
	
	/**
	 * @param id user id.
	 * @return User to which the specified id is mapped,
	 * 		if id is not mapped create a new guest with the given id. 
	 */
	public UserLocal getUser(String id) {
		UserLocal u = userMap.get(id);
		if (u == null)
			u = new Guest(id);
		return u;
	}
	
	/**
	 * Authenticate guest's identity checking given credentials. 
	 * @param g Guest to authenticate.
	 * @param n Guest's name.
	 * @param p Guest's password.
	 * @return true if the guest is successfully authenticated, false otherwise.
	 */
	public boolean authenticate(GuestLocal g, String n, String p) {
		EntityManager em = this.entManFactory.createEntityManager();
		// TODO check customer's name and password
		return false;
	}

	public boolean disconnect(CustomerLocal c) {
		if (this.userMap.remove(((UserLocal) c).getId()) != null)
			return true;
		else
			return false;
	}
}
