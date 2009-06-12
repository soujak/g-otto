/**
 * 
 */
package g8.bookshop.business.core;

import javax.ejb.Stateful;

/**
 * Stateful Session Bean implementation class Guest
 * @author soujak
 */
@Stateful
public class Guest extends User implements GuestLocal, GuestRemote {

	/**
	 * Minimal constructor
	 */
	public Guest() {
		super();
		this.customer = false;
	}
	
}
