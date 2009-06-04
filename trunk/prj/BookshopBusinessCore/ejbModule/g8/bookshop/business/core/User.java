/**
 * 
 */
package g8.bookshop.business.core;

/**
 * @author soujak
 *
 */
public abstract class User {
	private String id;
	private boolean customer;
	
	/**
	 * @return true if it is a customer, false if it is a guest 
	 */
	boolean isCustomer() {
		return this.customer;
	}
	
	/**
	 * @param id user id
	 * @param customer user type
	 */
	public User(String id, boolean customer) {
		this.id = id;
		this.customer = customer;
	}
}
