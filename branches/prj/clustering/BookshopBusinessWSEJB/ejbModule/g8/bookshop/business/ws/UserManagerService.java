package g8.bookshop.business.ws;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.um.UserManagerAdaptorRemote;
import g8.bookshop.business.util.BeanLocator;
import g8.bookshop.business.util.Name;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

/**
 * WebService Session Bean implementation class UserManagerService
 * @author soujak
 */
@Stateless
@WebService
public class UserManagerService implements UserManagerServiceRemote {
	
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
	public boolean Authenticate(String id, String name, String pwd) {;
		Logger logger = Logger.getLogger(UserManagerService.class);
		logger.info("authenticate: id "+id+", user "+name);
		UserManagerAdaptorRemote userManagerAdaptor;
		UserRemote user;
		boolean ret = false;
			try {
				logger.info("authenticate:    getting the corresponding user");
				userManagerAdaptor =
					(UserManagerAdaptorRemote) BeanLocator.getBean(
							Name.EJB.USERMANAGER_ADAPTOR_REMOTE);
				user = userManagerAdaptor.getUser(id);
				logger.info("authenticate:    got a "+(user.isCustomer()?"customer":"guest"));
				if (!user.isCustomer()) {
					logger.info("authenticate:    authenticating the guest");
					ret = userManagerAdaptor.authenticate((GuestRemote) user, name, pwd);
				}
			} catch (NamingException e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Disconnect a given user
	 * @param id user id
	 * @return true if the guest is successfully disconnected, false otherwise.
	 */
	@WebMethod
	public boolean Disconnect(String id) {
		Logger logger = Logger.getLogger(UserManagerService.class);
		logger.info("disconnect: id "+id);
		UserManagerAdaptorRemote userManagerAdaptor;
		boolean ret = false;
		try {
			userManagerAdaptor =
				(UserManagerAdaptorRemote) BeanLocator.getBean(
						Name.EJB.USERMANAGER_ADAPTOR_REMOTE);
			UserRemote user = userManagerAdaptor.lookup(id);
			if (user != null)
				if (user.isCustomer())
					ret = userManagerAdaptor.disconnect((CustomerRemote) user);
		} catch (NamingException e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return ret;
	}
}
