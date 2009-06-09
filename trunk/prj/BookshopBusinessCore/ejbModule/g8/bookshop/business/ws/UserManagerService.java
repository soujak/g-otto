package g8.bookshop.business.ws;
import g8.bookshop.business.core.UserManager;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Session Bean implementation class UserManagerService
 */
@WebService
@Stateless
public class UserManagerService {
	
	private UserManager um;

	/**
	 * Default constructor 
	 */
	public UserManagerService() {
		// TODO
//		this.um = UserManager.Instance(); 
	}
	
	/**
	 * Authenticate a user to become a customer
	 * @param id user id
	 * @param name customer name
	 * @param pwd customer password
	 * @return
	 */
	@WebMethod
	boolean Authenticate(String id, String name, String pwd) {
        // TODO
//		return um.authenticate(um.getUser(id), name, pwd);
		return false;
	}
	
	/**
	 * Disconnect a given user
	 * @param id user id
	 * @return
	 */
	@WebMethod
	boolean Disconnect(String id) {
        // TODO
//		return this.um.disconnect(this.um.getUser(id));
		return false;
	}	
}
