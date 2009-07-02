package g8.bookshop.business.ws;

import g8.bookshop.business.util.ConverterRemote;
import g8.bookshop.business.util.Name;
import g8.bookshop.persistence.Book;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * WebService Session Bean implementation class CatalogueService
 */
@Stateless
@WebService
public class CatalogueService implements CatalogueServiceRemote {
	
	@PersistenceContext(unitName="InformationManager")
	private EntityManager em;
	private ConverterRemote c;
	
    /**
     * Default constructor 
     * @throws NamingException 
     */
    public CatalogueService() throws NamingException {
    	super();
    	Properties env = new Properties();
    	env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
    		"org.jnp.interfaces.NamingContextFactory");
    	env.setProperty("jnp.partitionName",
    		"G8Business");
    	env.setProperty(Context.URL_PKG_PREFIXES,
    		"org.jboss.naming:org.jnp.interfaces");
    	Context ctx = new InitialContext(env);
    	this.c = (ConverterRemote) ctx.lookup(Name.EJB.CONVERTER_REMOTE);
    }
    
	/**
     * Advanced search
     * @param s book definition in XML format
     * @return corresponding books in XML format
     */
    // @SuppressWarnings("unchecked")
	@WebMethod
	public String Search(String s) {
		// TODO
//    	Book b = ((ConverterLocal)sessionContext.lookup("BookshopBusiness/Converter/local")).xmlToBook(s);
//    	List<Book> res = em.createNamedQuery("search").setParameter("Author", b.getAuthor())
//    		.setParameter("Editor", b.getEditor())
//    		.setParameter("ISBN", b.getISBN())
//    		.setParameter("Title", b.getTitle())
//    		.setParameter("Year", b.getYear())
//    		.getResultList();
//    	String ret = "";
//		try {
//			ret = Converter.toXML(res);
//		} catch(Exception e) {}
//		return ret;
    	return null;
	}
	/**
     * Full-text search
     * @param s simple string to search for
	 * @return corresponding books in XML format
	 */
    @SuppressWarnings("unchecked")
	@WebMethod
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String FullSearch(String s) {
    	Query q = em.createQuery(prepareFullSearchSQL(s));
		List<Book> res = q.getResultList();
		String ret = "";
		try {
			ret = c.booksToXML(res);
		} catch (IllegalArgumentException e) {} 
		catch (ParserConfigurationException e) {}
		catch (TransformerException e) {}
		return ret;
	}
    
    /**
	 * Prepare a SQL statement for a book full search. 
	 * @param s string to search
	 * @return the SQL statement
	 */
	private String prepareFullSearchSQL(String s) {
    	String[] strings = s.split(" ");
    	String SQLStatement = "SELECT b FROM Book b ";
    	if(strings.length > 0) {
    		SQLStatement += " WHERE b.Title LIKE '%" + strings[0] + "%'";
			SQLStatement += " OR b.Author LIKE '%" + strings[0] + "%'";
			SQLStatement += " OR b.Editor LIKE '%" + strings[0] + "%'";
			SQLStatement += " OR b.ISBN LIKE '%" + strings[0] + "%'";
			try {
				Integer year = Integer.parseInt(SQLStatement);
				SQLStatement += " OR b.Year = " + year;
			} catch (NumberFormatException e) {}
    	}
    	for (int i = 1; i < strings.length; i++) {
			String temp = strings[i];
			SQLStatement += " OR b.Title LIKE '%" + temp + "%'";
			SQLStatement += " OR b.Author LIKE '%" + temp + "%'";
			SQLStatement += " OR b.Editor LIKE '%" + temp + "%'";
			SQLStatement += " OR b.ISBN LIKE '%" + temp + "%'";
			try {
				Integer year = Integer.parseInt(temp);
				SQLStatement += " OR b.Year = " + year;
			} catch (NumberFormatException e) {}
		}
    	return SQLStatement;
	}
}
