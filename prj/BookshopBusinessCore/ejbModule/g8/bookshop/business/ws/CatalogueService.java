package g8.bookshop.business.ws;

import g8.bookshop.business.core.UserManagerLocal;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * WebService Session Bean implementation class CatalogueService
 */
@WebService
@Stateless
public class CatalogueService {

	private UserManagerLocal um;
	
	/**
     * Advanced search
     * @param id user id
     * @param s book definition in XML format
     * @return corresponding books in XML format
     */
	@WebMethod
	String Search(String id, String s) {
		// TODO
//		r = Book.find(s);
//		um.lookup(id).setLastSearchResult(r);
//		return r;
		return null;
	}
	/**
     * Full-text search
     * @param id user id 
     * @param s simple string to search for
	 * @return corresponding books in XML format
	 */
	@WebMethod
	String FullSearch(String id, String s) {
		// TODO
		return null;
	}
	
    /**
     * Default constructor 
     */
    public CatalogueService() {
        // TODO
    }
}
