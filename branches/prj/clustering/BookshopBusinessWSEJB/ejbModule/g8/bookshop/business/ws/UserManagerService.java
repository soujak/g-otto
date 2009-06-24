package g8.bookshop.business.ws;

import g8.bookshop.business.core.CustomerRemote;
import g8.bookshop.business.core.GuestRemote;
import g8.bookshop.business.core.UserRemote;
import g8.bookshop.business.singleton.UserManagerAdaptorRemote;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.Depends;

/**
 * WebService Session Bean implementation class UserManagerService
 * @author soujak
 */
@Depends(value="ear=BookshopBusinessSingleton.ear,jar=BookshopBusinessSingletonEJB.jar,name=UserManagerAdaptor,service=EJB3")
@Stateless
@WebService
public class UserManagerService implements UserManagerServiceRemote {
	
	private UserManagerAdaptorRemote um;
	
	/**
	 * Constructor 
	 * @throws NamingException
	 */
	public UserManagerService() throws NamingException {
		super();
    	Properties env = new Properties();
    	env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
    		"org.jnp.interfaces.NamingContextFactory");
    	env.setProperty("jnp.partitionName",
    		"G8Business");
    	env.setProperty(Context.URL_PKG_PREFIXES,
    		"org.jboss.naming:org.jnp.interfaces");
    	Context ctx = new InitialContext(env);
    	this.um = (UserManagerAdaptorRemote) ctx
    		.lookup("BookshopBusinessSingleton/UserManagerAdaptor/remote");
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
		UserRemote user;
		boolean ret = false;
		try {
			user = um.getUser(id);
			System.out.println("UserManagerService: auth("+id+", "+name+", "+pwd+")");
			if (!user.isCustomer())
				ret = um.authenticate((GuestRemote) user, name, pwd);
		} catch (NamingException e) {}
		return ret;
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
