package g8.bookshop.business.ws;

import javax.ejb.Remote;

/**
 * WebService Session Bean remote interface CatalogueServiceRemote
 */

@Remote
public interface CatalogueServiceRemote {
	
	/**
     * Advanced search
	 * @param s book definition in XML format
     * @return corresponding books in XML format
     */
	String Search(String s);

	/**
     * Full-text search
	 * @param s simple string to search for
     * @return corresponding books in XML format
	 */
	String FullSearch(String s);
}