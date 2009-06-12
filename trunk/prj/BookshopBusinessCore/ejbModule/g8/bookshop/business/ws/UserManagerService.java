package g8.bookshop.business.ws;

import g8.bookshop.business.core.Customer;
import g8.bookshop.business.core.Guest;
import g8.bookshop.business.core.User;
import g8.bookshop.business.core.UserManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * WebService Session Bean implementation class UserManagerService
 * @author soujak
 */
@Stateless
@WebService
public class UserManagerService implements UserManagerServiceRemote {
	
	@EJB
	private UserManager um;

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
	@WebMethod
	public boolean Authenticate(String id, String name, String pwd) {
		User user = um.getUser(id);
		if (!user.isCustomer())
			return um.authenticate((Guest) user, name, pwd);
		else
			return false;
	}
	
	/**
	 * Disconnect a given user
	 * @param id user id
	 * @return
	 */
	@WebMethod
	public boolean Disconnect(String id) {
		User user = um.lookup(id);
		if (user != null)
			if (user.isCustomer())
				return um.disconnect((Customer) user);
			else
				return false;
		return false;
	}
}
