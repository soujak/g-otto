package g8.bookshop.presentation.servlet.shoppingcart;

import g8.bookshop.business.ws.ShoppingCartService;
import g8.bookshop.business.ws.ShoppingCartServiceServiceLocator;
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
 * Servlet implementation class AddOrders
 */
public class AddOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrders() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieves user session...
		HttpSession session = request.getSession();
		// retrieves DataExchage user instance...
		DataExchange dataExchange = Utils.getDataExchange(session);
		// retrieves session id
		String id = session.getId();
		
		// initialize orders xml string
		String orders = "<orders />";
		
		// initialize shoppingcart view result
		String xml_shoppingcart = "<shoppingcart />";
			
		// initialize service port...
		try {
			ShoppingCartService service = (new ShoppingCartServiceServiceLocator()).getShoppingCartServicePort();
			service.addOrders(id, orders);
			service.view(id);
			dataExchange.setShoppingcart(xml_shoppingcart);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("DataExchange", dataExchange);
		Utils.forwardToPage("/pages/cart.jsp", getServletContext(),
				request, response);
	}

}
