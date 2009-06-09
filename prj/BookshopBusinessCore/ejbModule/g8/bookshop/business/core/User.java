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
public abstract class User implements UserLocal {
	private String id;
	private boolean customer;
	private String lastSearchResults;
	
	/**
	 * @param id user id
	 * @param customer user type
	 */
	public User(String id, boolean customer) {
		this.id = id;
		this.customer = customer;
	}
	
	/**
	 * @return user id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Check the kind of the user
	 * @return true if it is a customer, false if it is a guest 
	 */
	public boolean isCustomer() {
		return this.customer;
	}
}
