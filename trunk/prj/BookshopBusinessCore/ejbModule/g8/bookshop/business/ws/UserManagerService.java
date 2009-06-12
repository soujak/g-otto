package g8.bookshop.business.ws;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.core.UserManagerLocal;

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
	@WebMethod
	public boolean Authenticate(String id, String name, String pwd) {
		UserRemote user = um.getUser(id);
		if (!user.isCustomer())
			return um.authenticate((GuestRemote) user, name, pwd);
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
		UserRemote user = um.lookup(id);
		if (user != null)
			if (user.isCustomer())
				return um.disconnect((CustomerRemote) user);
			else
				return false;
		return false;
	}
}
