package g8.bookshop.business.core;

import javax.ejb.Remote;

/**
 * Interface User
 * @author soujak
 */
@Remote
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

	/**
	 * @param id the id to set
	 */
	public void setId(String id);
}