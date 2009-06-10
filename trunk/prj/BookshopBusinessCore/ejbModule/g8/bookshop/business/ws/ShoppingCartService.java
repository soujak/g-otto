package g8.bookshop.business.ws;

// import g8.bookshop.business.core.CustomerLocal;
// import g8.bookshop.business.core.ShoppingCartLocal;
// import g8.bookshop.business.core.UserManagerLocal;


/**
 * WebService Session Bean implementation class ShoppingCartService
 */
public class ShoppingCartService implements ShoppingCartServiceRemote {
	
//	private UserManagerLocal um;
	
	/**
	 * Default constructor
	 */
	public ShoppingCartService() {
		// TODO
	}

	/**
	 * @param id User id
	 * @return Shopping cart in XML format
	 */
	public String View(String id) {
		 // TODO
//		return um.getCustomer(id).getShoppingCart().toXML;
		return null;
	}
	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return
	 */
	public boolean AddOrders(String id, String ords) {
		 // TODO
//		orders=ords.toOrder;
//		return um.getCustomer(id).getShoppingCart().addOrders(orders);
		return false;
	}
	/**
	 * @param id User id
	 * @param ords Orders in XML format
	 * @return 
	 */
	public boolean Update(String id, String ords) {
		 // TODO
		return false;
	}
	/**
	 * @param id User id
	 * @return
	 */
	public boolean Checkout(String id) {
		 // TODO
		return false;
	}	
}
