/**
 * 
 */
package g8.bookshop.business.core;

import javax.ejb.Stateful;

/**
 * Stateful Session Bean implementation class User
 * @author soujak
 */
@Stateful
public abstract class User implements UserLocal, UserRemote {
	private String id;
	protected boolean customer;
	// TODO use me, please
	private String lastSearchResults;
	
	/**
	 * @return user id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Check the kind of the user
	 * @return true if it is a customer, false if it is a guest 
	 */
	public boolean isCustomer() {
		return this.customer;
	}
}
