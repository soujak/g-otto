package g8.bookshop.business.ws;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * WebService Session Bean remote interface UserManagerServiceRemote
 * @author soujak
 */
@WebService
@Remote
public interface UserManagerServiceRemote {
	/**
	 * Authenticate guest's identity checking given credentials.
	 * @param id Guest's id
	 * @param name customer name
	 * @param pwd customer password
	 * @return true if the guest is successfully authenticated, false otherwise.
	 */
	@WebMethod
	boolean Authenticate(String id, String name, String pwd);
	
	/**
	 * Disconnect a given user
	 * @param id user id
	 * @return
	 */
	@WebMethod
	boolean Disconnect(String id);
}