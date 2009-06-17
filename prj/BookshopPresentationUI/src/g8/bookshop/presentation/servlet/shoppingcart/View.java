package g8.bookshop.presentation.servlet.shoppingcart;

import g8.bookshop.business.ws.ShoppingCartServiceServiceLocator;
import g8.bookshop.presentation.Constants;
import g8.bookshop.presentation.content.manager.DataExchange;
import g8.bookshop.presentation.servlet.Utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.xml.sax.SAXException;

/**
 * Servlet implementation class View
 */
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieves user session...
		HttpSession session = request.getSession();
		// retrieves DataExchage user instance...
		DataExchange dataExchange = Utils.getDataExchange(session);
		
		// initialize shoppingcart xml string
		String xml_shoppingcart = "<shoppingcart />";
		
		// initialize message string
		dataExchange.setMessage("");
		
		// invoke shoppingcart service method...
		try {
			// ... to view cart
			xml_shoppingcart = (new ShoppingCartServiceServiceLocator()).getShoppingCartServicePort().view(session.getId());
			// fills dataExchange variable
			dataExchange.setShoppingcart(xml_shoppingcart);
		} catch (Exception e) {
			dataExchange.setMessage("View shoppingcart failed: an error occurred.");
			e.printStackTrace();
		}
		
		// forward request to cart page
		Utils.forwardToPage(Constants.JSP_CART, getServletContext(),
				request, response);		
	}

}
