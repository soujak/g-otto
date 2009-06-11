package g8.bookshop.business.ws;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * WebService Session Bean remote interface CatalogueServiceRemote
 */

@Remote
@WebService
public interface CatalogueServiceRemote {
	
	/**
     * Advanced search
	 * @param s book definition in XML format
     * @return corresponding books in XML format
     */
	@WebMethod
	String Search(String s);

	/**
     * Full-text search
	 * @param s simple string to search for
     * @return corresponding books in XML format
	 */
	@WebMethod
	String FullSearch(String s);
}