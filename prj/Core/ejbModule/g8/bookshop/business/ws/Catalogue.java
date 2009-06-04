package g8.bookshop.business.ws;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Session Bean implementation class Catalogue
 */
@WebService
@Stateless
public class Catalogue {

	/**
     * Advanced search
     * @param id user id
     * @param s book definition in XML format
     * @return corresponding books in XML format
     */
	@WebMethod
	String Search(String id, String s) {
		// TODO
		return null;
	}
	/**
     * Full-text search
     * @param id user id 
     * @param s simple string
	 * @return corresponding books in XML format
	 */
	@WebMethod
	String FullSearch(String s) {
		// TODO
		return null;
	}
	
    /**
     * Default constructor 
     */
    public Catalogue() {
        // TODO
    }
}