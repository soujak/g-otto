package g8.bookshop.business.um;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;

import org.jboss.ejb3.annotation.Management;

@Management
public interface UserManagerMBean {
	/**
	 * Lookup for a user in the bookshop from his id
	 * @param id user id
	 * @return user to which the specified id is mapped, or null if id does not exists 
	 */
	public UserRemote lookup(String id);
	/**
	 * Search a user in the bookshop from his id (possibly new) 
	 * @param id user id.
	 * @return User to which the specified id is mapped,
	 * 		if id is not mapped create a new guest with the given id. 
	 */
	public UserRemote getUser(String id);
	/**
	 * Authenticate a customer in the bookshop, verifying his credentials 
	 * @param g Guest to authenticate
	 * @param n Guest's name
	 * @param p Guest's password
	 * @return true if the guest is successfully authenticated, false otherwise
	 */
	public boolean authenticate(GuestRemote g, String n, String p);
	/**
	 * Disconnect a given customer from the bookshop
	 * @param customerRemote Customer to disconnect
	 * @return true if the customer is successfully disconnected, false otherwise
	 */
	public boolean disconnect(CustomerRemote customerRemote);
	
	/**
	 * @throws Exception
	 */
	void create() throws Exception;
	/**
	 * @throws Exception
	 */
	void start() throws Exception;
	/**
	 * 
	 */
	void stop();
	/**
	 * 
	 */
	void destroy();
	
	/**
	 * @return
	 */
	boolean isMasterNode();
	/**
	 * 
	 */
	void startSingleton();
	/**
	 * 
	 */
	void stopSingleton();
}
