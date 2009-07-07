package g8.bookshop.business.ws;

import javax.ejb.Remote;

/**
 * TODO javadoc
 * WebService Session Bean remote interface UserManagerServiceRemote
 * @author soujak
 */
@Remote
public interface UserManagerServiceRemote {
	/**
	 * Authenticate guest's identity checking given credentials.
	 * @param id Guest's id
	 * @param name customer name
	 * @param pwd customer password
	 * @return true if the guest is successfully authenticated, false otherwise.
	 */
	boolean Authenticate(String id, String name, String pwd);
	
	/**
	 * Disconnect a given user.
	 * @param id user id to disconnect
	 * @return true if the customer is successfully disconnected, false otherwise. 
	 */
	boolean Disconnect(String id);
}