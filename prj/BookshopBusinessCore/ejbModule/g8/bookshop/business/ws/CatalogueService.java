package g8.bookshop.business.ws;

import g8.bookshop.business.core.UserManagerLocal;

/**
 * WebService Session Bean implementation class CatalogueService
 */
public class CatalogueService implements CatalogueServiceRemote {
	
	private UserManagerLocal um;
	
	
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
	public
	String Search(String s) {
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
	public
	String FullSearch(String s) {
		// TODO
		return null;
	}
}
