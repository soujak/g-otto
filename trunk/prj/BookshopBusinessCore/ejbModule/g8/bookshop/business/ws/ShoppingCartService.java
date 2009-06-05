package g8.bookshop.business.ws;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Session Bean implementation class ShoppingCartService
 */
@WebService
@Stateless
public class ShoppingCartService {

	/**
	 * @param id User id
	 * @return Shopping cart in XML format
	 */
	@WebMethod
	String View(String id) {
		 // TODO
		return null;
	}
	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return
	 */
	@WebMethod
	boolean AddOrders(String id, String ords) {
		 // TODO
		return false;
	}
	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return 
	 */
	@WebMethod
	boolean Update(String id, String ords) {
		 // TODO
		return false;
	}
	/**
	 * @param id User id
	 * @return
	 */
	@WebMethod
	boolean Checkout(String id) {
		 // TODO
		return false;
	}
	
    /**
     * Default constructor
     */
    public ShoppingCartService() {
        // TODO
    }
}
