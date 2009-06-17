package g8.bookshop.businness.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import g8.bookshop.business.ws.ShoppingCartServiceRemote;
import g8.bookshop.business.ws.UserManagerServiceRemote;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShoppingCartServiceTest {
	
	static private Context ctx = null;
	static private ShoppingCartServiceRemote sc = null;
	static private UserManagerServiceRemote ums = null;
	final static private String session = "session1";
	final static private String username = "gnappo";
	final static private String password = "gnappo";
	final static private String order1 = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<orders>" +
			"<order bookid=\"1\" quantity=\"1\"/>" +
			"<order bookid=\"2\" quantity=\"1\"/>" +
		"</orders>";
	final static private String order2 = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<orders>" +
			"<order bookid=\"1\" quantity=\"5\"/>" +
		"</orders>";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		env.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099");
		env.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		ctx = new InitialContext(env);
		sc = (ShoppingCartServiceRemote) ctx
				.lookup("BookshopBusinessWS/ShoppingCartService/remote");
		ums = (UserManagerServiceRemote) ctx
		.lookup("BookshopBusinessWS/UserManagerService/remote");
	}
	
	@Before
	public void setUp() throws Exception {
		ums.Authenticate(session, username, password);
	}
	
	@After
	public void tearDown() throws Exception {
		ums.Disconnect(session);
	}

	@Test
	public final void testView() {
		String XMLExpected =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			"<shoppingcart/>";
		assertEquals(XMLExpected, sc.view(session));
	}

	@Test
	public final void testAddOrders() {
		assertTrue(sc.addOrders(session, order1));
	}

	@Test
	public final void testUpdate() {
		assertTrue(sc.update(session, order1));
	}

	@Test
	public final void testCheckOut() {
		assertTrue(sc.checkOut(session));
	}
	
	@Test
	public final void testCompleteShoppingCart() {
		String XMLExpected1 = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<shoppingcart>" +
				"<stock quantity=\"6\">" +
					"<book author=\"autore1\" editor=\"editore1\" id=\"1\" isbn=\"isbn1\" title=\"titolo1\" year=\"1\"/></stock><stock quantity=\"1\"><book author=\"autore2\" editor=\"editore2\" id=\"2\" isbn=\"isbn2\" title=\"titolo2\" year=\"2\"/>" +
				"</stock>" +
			"</shoppingcart>";
		String XMLExpected2 = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			"<shoppingcart>" +
				"<stock quantity=\"5\">" +
					"<book author=\"autore1\" editor=\"editore1\" id=\"1\" isbn=\"isbn1\" title=\"titolo1\" year=\"1\"/>" +
				"</stock>" +
			"</shoppingcart>";
		String XMLExpected3 =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			"<shoppingcart/>";
		assertTrue(sc.addOrders(session, order1));
		assertTrue(sc.addOrders(session, order2));
		assertEquals(XMLExpected1, sc.view(session));
		assertTrue(sc.update(session, order2));
		assertEquals(XMLExpected2, sc.view(session));
		assertTrue(sc.checkOut(session));
		assertEquals(XMLExpected3, sc.view(session));
	}

}
