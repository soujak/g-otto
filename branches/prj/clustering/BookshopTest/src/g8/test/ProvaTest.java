package g8.test;


import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProvaTest {

	static private Context ctx;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
//		env.setProperty(Context.PROVIDER_URL, "192.168.1.2:1100");
//		192.168.1.1:1100
		env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		env.setProperty("jnp.partitionName","G8Business");
		ctx = new InitialContext(env);
	}

	@Test
	public void testSingleton() throws NamingException {
		ProvaRemote p = (ProvaRemote) ctx.lookup("ProvaEAR/Prova/remote");
		int first = p.inc();
		System.out.println("First increment: value = "+first);
		int second = p.inc();
		System.out.println("second increment: value = "+second);
		boolean res = (second - first == 1);
		assertTrue(res);
	}
}
