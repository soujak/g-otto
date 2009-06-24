/**
 * 
 */
package g8.bookshop.business.util;

import g8.bookshop.business.core.GuestRemote;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.Clustered;

/**
 * @author soujak
 *
 */
public class BeanLocator {

	/**
	 * Lookup for a JNDI address in the default JNP context.
	 * @param a
	 * 			JNDI address of the beans
	 * @return an instance of the bean if it is found in the current context,
	 * 			null otherwise.
	 * @throws NamingException 
	 */
	public static Object getBean (String a) throws NamingException {
		return BeanLocator.getBean("jnp.partitionName", "G8Business", a);
	}

	/**
	 * Lookup for a JNDI address in the specified JNP context.
	 * @param p
	 * 			JNP type
	 * @param v
	 * 			JNP address
	 * @param a
	 * 			JNDI address of the bean
	 * @return an instance of the bean if it is found in the current context,
	 * 			null otherwise.
	 * @throws NamingException
	 */
	public static Object getBean (String p, String v, String a) throws NamingException {
		Object bean = null;
		Properties env = new Properties();
		InitialContext ctx;
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
		"org.jnp.interfaces.NamingContextFactory");
		env.setProperty(p, v);
		env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		ctx = new InitialContext(env);
		bean = (GuestRemote) ctx.lookup(a);
		return bean;
	}

}
