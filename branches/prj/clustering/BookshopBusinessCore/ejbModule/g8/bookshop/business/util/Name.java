package g8.bookshop.business.util;

public abstract class Name {
	public abstract class EJB {
		// g8.bookshop.business
		public static final String BUSINESS = "BookshopBusiness";

		// g8.bookshop.business.ws
		public static final String CATALOGUESERVICE = BUSINESS + "/CatalogueService";
		public static final String CATALOGUESERVICE_REMOTE = CATALOGUESERVICE + "/remote";
		public static final String SHOPPINGCARTSERVICE = BUSINESS + "/ShoppingCartService";
		public static final String SHOPPINGCARTSERVICE_REMOTE = SHOPPINGCARTSERVICE + "/remote";
		public static final String USERMANAGERSERVICE = BUSINESS + "/UserManagerService";
		public static final String USERMANAGERSERVICE_REMOTE = USERMANAGERSERVICE + "/remote";

		// g8.bookshop.business.core
		public static final String GUEST = BUSINESS + "/Guest";
		public static final String GUEST_REMOTE = GUEST + "/remote";
		public static final String CUSTOMER = BUSINESS + "/Customer";
		public static final String CUSTOMER_REMOTE = CUSTOMER + "/remote";
		public static final String SHOPPINGCART = BUSINESS + "/ShoppingCart";
		public static final String SHOPPINGCART_REMOTE = SHOPPINGCART + "/remote";
		public static final String ORDER = BUSINESS + "/Order";
		public static final String ORDER_REMOTE = ORDER + "/remote";

		// g8.bookshop.business.util
		public static final String CONVERTER = BUSINESS + "/Converter";
		public static final String CONVERTER_REMOTE = CONVERTER + "/remote";
		
		// g8.bookshop.business.um
		public static final String USERMANAGER_ADAPTOR = BUSINESS + "/UserManagerAdaptor";
		public static final String USERMANAGER_ADAPTOR_REMOTE = USERMANAGER_ADAPTOR + "/remote";

		// org.jboss.jmx.adaptor.rmi
		public static final String JMX_RMIADAPTOR = "jmx/rmi/RMIAdaptor";
	}
	public abstract class JMX {
		public static final String USERMANAGER_MBEAN = "g8.bookshop.business.um:service=UserManager";
		public static final String HAG8PARTITION_MBEAN = "jboss:service=HAPartition,partition=G8Business";
	}
}
