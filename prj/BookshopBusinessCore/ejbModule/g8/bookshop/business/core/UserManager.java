/**
 * 
 */
package g8.bookshop.business.core;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.ejb.Stateful;

/**
 * @author soujak
 *
 */
@Stateful
public class UserManager {
	private static UserManager uniqueUserManager = null;
	private static SortedMap<String,User> userMap;
	
	
	/**
	 * @param id user id
	 * @return user to which the specified id is mapped, or null if id does not exists 
	 */
	User lookup(String id) {
		return userMap.get(id);
	}
	
	/**
	 * @return the unique instance of UserManager
	 */
	static UserManager Instance() {
		if (uniqueUserManager == null)
			uniqueUserManager = new UserManager();
		return uniqueUserManager;
	}
	
	private UserManager() {
		userMap = Collections.synchronizedSortedMap(new TreeMap<String,User>());
		// TODO
	}
}
