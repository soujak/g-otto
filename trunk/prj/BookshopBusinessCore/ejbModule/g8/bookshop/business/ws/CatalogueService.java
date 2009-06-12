package g8.bookshop.business.ws;

import g8.bookshop.business.util.Converter;
import g8.bookshop.business.util.PrepareSQLStatement;
import g8.bookshop.persistence.Book;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



/**
 * WebService Session Bean implementation class CatalogueService
 */
@Stateless
@WebService
public class CatalogueService implements CatalogueServiceRemote {
	
	@PersistenceContext(unitName="InformationManager")
	private EntityManager em;
	
    /**
     * Default constructor 
     */
    public CatalogueService() {
        // TODO
    }
    
	/**
     * Advanced search
     * @param s book definition in XML format
     * @return corresponding books in XML format
     */
    @SuppressWarnings("unchecked")
	@WebMethod
	public String Search(String s) {
		// TODO
//    	Book b = Converter.XMLtoBook(s);
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
    	Query q = em.createQuery(PrepareSQLStatement.prepareFullSearchSQL(s));
		List<Book> res = q.getResultList();
		String ret = "";
		try {
			ret = Converter.toXML(res);
		} catch(Exception e) {}
		return ret;
	}
}
