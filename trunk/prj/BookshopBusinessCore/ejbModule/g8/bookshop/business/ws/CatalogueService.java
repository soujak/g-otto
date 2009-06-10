package g8.bookshop.business.ws;

import g8.bookshop.business.core.UserManagerLocal;

/**
 * WebService Session Bean implementation class CatalogueService
 */
public class CatalogueService implements CatalogueServiceRemote {
	
	private UserManagerLocal um;
	
	/**
     * Advanced search
     * @param id user id
     * @param s book definition in XML format
     * @return corresponding books in XML format
     */
	public
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
	public
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
