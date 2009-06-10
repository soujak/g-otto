package g8.bookshop.business.ws;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * WebService Session Bean remote interface ShoppingCartServiceRemote
 */
@WebService
@Stateless
@Remote
public interface ShoppingCartServiceRemote {

	/**
	 * @param id User id
	 * @return Shopping cart in XML format
	 */
	@WebMethod
	String View(String id);

	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return
	 */
	@WebMethod
	boolean AddOrders(String id, String ords);

	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return 
	 */
	@WebMethod
	boolean Update(String id, String ords);
	
	/**
	 * @param id User id
	 * @return
	 */
	@WebMethod
	boolean Checkout(String id);
}