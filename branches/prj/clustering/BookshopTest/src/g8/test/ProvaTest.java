package g8.test;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.jmx.adaptor.rmi.RMIAdaptor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProvaTest {

	private MBeanServerConnection adaptor;
	
	
	@Before
	public void setUpBeforeClass() throws Exception {
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
//		env.setProperty(Context.PROVIDER_URL, "192.168.1.2:1100");
//		192.168.1.1:1100
		env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		env.setProperty("jnp.partitionName","G8Business");
		Context ctx = new InitialContext(env);
		this.adaptor = (RMIAdaptor) ctx.lookup("jmx/rmi/RMIAdaptor");
	}

	public boolean testInc()
	throws NamingException, MalformedObjectNameException, InstanceNotFoundException, MBeanException, ReflectionException, IOException
	{
		boolean res = false;
		int first, second = 0;
		ObjectName name = new ObjectName("g8.test:service=Prova");
		if (adaptor.isRegistered(name)) {
			first = ((Integer) this.adaptor.invoke(name, "inc", null, null)).intValue();
			System.out.println("First increment: value = "+first);
			second = ((Integer) this.adaptor.invoke(name, "inc", null, null)).intValue();
			System.out.println("second increment: value = "+second);
			res = (second - first == 1);
		}
		return res;
	}
	@Test
	public void intensiveTestInc()
	throws NamingException, MalformedObjectNameException, InstanceNotFoundException, MBeanException, ReflectionException, IOException
	{
		int rounds = 100;
		boolean res = true;
		while (rounds > 1) {
			res = res && testInc();
			rounds--;
		}
		assertTrue(res);
	}
}
