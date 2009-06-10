package g8.bookshop.business.ws;

import g8.bookshop.business.core.CustomerLocal;
import g8.bookshop.business.core.GuestLocal;
import g8.bookshop.business.core.UserLocal;
import g8.bookshop.business.core.UserManagerLocal;

/**
 * WebService Session Bean implementation class UserManagerService
 * @author soujak
 */
public class UserManagerService implements UserManagerServiceRemote {
	
	private UserManagerLocal um;

	/**
	 * Constructor 
	 */
	public UserManagerService() {
		super();
	}
	
	/**
	 * Authenticate guest's identity checking given credentials.
	 * @param id Guest's id
	 * @param name customer name
	 * @param pwd customer password
	 * @return true if the guest is successfully authenticated, false otherwise.
	 */
	public boolean Authenticate(String id, String name, String pwd) {
		UserLocal user = um.getUser(id);
		if (!user.isCustomer())
			return um.authenticate((GuestLocal) user, name, pwd);
		else
			return false;
	}
	
	/**
	 * Disconnect a given user
	 * @param id user id
	 * @return
	 */
	public boolean Disconnect(String id) {
		UserLocal user = um.lookup(id);
		if (user != null)
			if (user.isCustomer())
				return um.disconnect((CustomerLocal) user);
			else
				return false;
		return false;
	}
}
