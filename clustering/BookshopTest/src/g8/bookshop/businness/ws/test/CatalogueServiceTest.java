package g8.bookshop.businness.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import g8.bookshop.business.ws.CatalogueServiceRemote;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CatalogueServiceTest {

	static private Context ctx = null;
	static private CatalogueServiceRemote cs = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Properties env = new Properties();
		env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		env.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099");
		env.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		ctx = new InitialContext(env);
		cs = (CatalogueServiceRemote) ctx
				.lookup("BookshopBusiness/CatalogueService/remote");
	}
	
	@Ignore
	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testFullSearch() {
		String XMLExpected = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<books>" +
				"<book author=\"autore1\" editor=\"editore1\" id=\"1\" isbn=\"isbn1\" title=\"titolo1\" year=\"1\"/>" +
			"</books>";
		assertEquals(XMLExpected, cs.FullSearch("isbn1"));
	}

}
