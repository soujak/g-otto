package g8.bookshop.business.um;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;

import javax.ejb.Remote;
import javax.naming.NamingException;

/**
 * Remote interface UserManager
 * @author soujak
 */
@Remote
public interface UserManagerAdaptorRemote {
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
	 * @throws NamingException 
	 */
	public UserRemote getUser(String id) throws NamingException;
	
	/**
	 * Authenticate a customer in the bookshop, verifying his credentials 
	 * @param g Guest to authenticate
	 * @param n Guest's name
	 * @param p Guest's password
	 * @return true if the guest is successfully authenticated, false otherwise
	 * @throws NamingException 
	 */
	public boolean authenticate(GuestRemote g, String n, String p) throws NamingException;
	
	/**
	 * Disconnect a given customer from the bookshop
	 * @param customerRemote Customer to disconnect
	 * @return true if the customer is successfully disconnected, false otherwise
	 */
	public boolean disconnect(CustomerRemote customerRemote);
}