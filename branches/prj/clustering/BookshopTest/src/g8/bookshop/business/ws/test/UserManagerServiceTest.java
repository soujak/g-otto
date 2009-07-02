package g8.bookshop.business.ws.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import g8.bookshop.business.util.Name;
import g8.bookshop.business.ws.UserManagerServiceRemote;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserManagerServiceTest {

	static private Context ctx = null;
	static private UserManagerServiceRemote ums = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		env.setProperty("jnp.partitionName", "G8Business");
		env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		ctx = new InitialContext(env);
		ums = (UserManagerServiceRemote) ctx.lookup(
				Name.EJB.USERMANAGERSERVICE_REMOTE);
	}

	@Test
	public void testAuthenticateDisconnect() {
		boolean res = false;
		res = ums.Authenticate("session1", "gnappo", "gnappo");
		res = !ums.Authenticate("session1", "gnappo", "gnappo") && res;
		res = ums.Disconnect("session1") && res;
		res = !ums.Disconnect("session1") && res;
		assertTrue(res);
	}
	
	@Test
	public void testAuthenticateNonExistentUser() {
		boolean res = false;
		res = ums.Authenticate("session1", "1234567890", "1234567890");
		assertFalse(res);
	}

	@Test
	public void testHAAuthenticateDisconnect() {
		boolean res = true;
		int rounds=10;
		System.out.print("|");
		for (int i=0; i<rounds; i++) {
			System.out.print(" ");
		}
		System.out.println("|");
		System.out.print("|");
		for (int i=0; i<rounds; i++) {
			res = ums.Authenticate("session"+i, "gnappo", "gnappo") && res;
			System.out.print("=");
		}
		System.out.println("|");
		System.out.print("|");
		for (int i=0; i<rounds; i++) {
			res = ums.Disconnect("session"+i) && res;
			System.out.print("=");
		}
		System.out.println("|");
		assertTrue(res);
	}
}
