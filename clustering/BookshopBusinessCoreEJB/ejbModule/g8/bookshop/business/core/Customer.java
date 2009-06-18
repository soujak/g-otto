package g8.bookshop.business.core;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.Clustered;

/**
 * Stateful Session Bean implementation class Customer
 * @author soujak
 */
@Clustered
@Stateful
public class Customer extends User implements CustomerLocal, CustomerRemote {

	private ShoppingCartRemote shoppingCart;

	/**
	 * @see CustomerRemote#getShoppingCart()
	 */
	public ShoppingCartRemote getShoppingCart() {
		System.out.println(this.getId() + ": getShoppingCart()");
		return shoppingCart;
	}

	/**
	 * Minimal constructor
	 */
	public Customer() {
		super();
		customer = true;
	}
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void createShoppingCart() throws NamingException {
		Properties env = new Properties();
		InitialContext ctx;
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		env.setProperty("jnp.partitionName", "G8Business");
		env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		ctx = new InitialContext(env);
		this.shoppingCart = (ShoppingCartRemote) ctx.lookup("BookshopBusinessCore/ShoppingCart/remote");
	}
}
