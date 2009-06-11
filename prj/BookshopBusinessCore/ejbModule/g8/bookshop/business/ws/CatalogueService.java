package g8.bookshop.business.ws;

import g8.bookshop.business.util.Converter;
import g8.bookshop.persistence.Book;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 * WebService Session Bean implementation class CatalogueService
 */
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
	public String Search(String s) {
		// TODO
//		r = Book.find(s);
//		return r;
		return null;
	}
	/**
     * Full-text search
     * @param s simple string to search for
	 * @return corresponding books in XML format
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String FullSearch(String s) {
		List<Book> res = em.createNamedQuery("fullSearch")
			.setParameter("arg",s).getResultList();
		return Converter.toXML(res);
	}
}
