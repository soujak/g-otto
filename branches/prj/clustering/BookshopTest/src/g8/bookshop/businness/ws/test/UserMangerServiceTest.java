package g8.bookshop.businness.ws.test;

import static org.junit.Assert.assertTrue;
import g8.bookshop.business.ws.UserManagerServiceRemote;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserMangerServiceTest {

	static private Context ctx = null;
	static private UserManagerServiceRemote ums = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		env.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099");
		env.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		ctx = new InitialContext(env);
		ums = (UserManagerServiceRemote) ctx
				.lookup("BookshopBusiness/UserManagerService/remote");
	}

	@Test
	public void testAuthenticateDisconnect() {
		boolean res = false;
		res = ums.Authenticate("session1", "gnappo", "gnappo");
		res = !ums.Authenticate("session1", "gnappo", "gnappo") && res;
		res = ums.Disconnect("session1") && res;
		assertTrue(res);
	}

}
