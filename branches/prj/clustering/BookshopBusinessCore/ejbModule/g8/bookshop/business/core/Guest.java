/**
 * 
 */
package g8.bookshop.business.core;

import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.Clustered;

/**
 * Stateful Session Bean implementation class Guest
 * @author soujak
 */
@Clustered
@Stateful
public class Guest extends User implements GuestRemote {

	/**
	 * Minimal constructor
	 */
	public Guest() {
		super();
		this.customer = false;
	}
	
}
