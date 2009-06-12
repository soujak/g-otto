package g8.bookshop.business.core;

import javax.ejb.Local;

/**
 * Interface User
 * @author soujak
 */
@Local
public interface UserRemote {
	/**
	 * @return user unique id
	 */
	public String getId();
	
	/**
	 * Check the kind of the user
	 * @return true if it is a customer, false if it is a guest 
	 */
	public boolean isCustomer();
}