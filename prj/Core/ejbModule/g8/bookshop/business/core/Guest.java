/**
 * 
 */
package g8.bookshop.business.core;

import javax.ejb.Stateful;

/**
 * @author soujak
 *
 */
@Stateful
public class Guest extends User {

	/**
	 * @param id user id
	 */
	public Guest(String id) {
		super(id, false);
	}
	
}
