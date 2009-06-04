package g8.bookshop.business.ws;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Session Bean implementation class UserManager
 */
@WebService
@Stateless
public class UserManager {

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
		return false;
	}
	
    /**
     * Default constructor 
     */
    public UserManager() {
        // TODO
    }

}
