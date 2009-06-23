package g8.bookshop.presentation.servlet.shoppingcart;

import g8.bookshop.business.ws.ShoppingCartService;
import g8.bookshop.business.ws.ShoppingCartServiceServiceLocator;
import g8.bookshop.presentation.Constants;
import g8.bookshop.presentation.content.manager.DataExchange;
import g8.bookshop.presentation.servlet.Utils;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieves user session...
		HttpSession session = request.getSession();
		// retrieves DataExchage user instance...
		DataExchange dataExchange = Utils.getDataExchange(session);
		// retrieves session id
		String id = session.getId();
		
		// initialize orders xml string
		String xml_orders = "<orders />";
		
		// initialize shoppingcart view result
		String xml_shoppingcart = "<shoppingcart />";
		
		// initialize message string
		dataExchange.setMessage("");
			
		try {
			// initialize service port...
			Enumeration params = request.getParameterNames();
			if(params.hasMoreElements()) {
				String param;
				Document document;
				Element root;
				Element item;
				// create DOMDocument
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				root = document.createElement("orders");
				document.appendChild(root);
				while(params.hasMoreElements()) {
					param = (String)params.nextElement();
					if (param.startsWith("book")) {
						item = document.createElement("order");
						item.setAttribute("bookid", request.getParameter(param));
						item.setAttribute("quantity", "1");
						root.appendChild(item);
					}
				}
				xml_orders = Utils.xmlDocumentToString(document);
			}
			
			ShoppingCartService service = (new ShoppingCartServiceServiceLocator()).getShoppingCartServicePort();
			// invoke shopping cart service method to add item to cart
			if(!(service.addOrders(id, xml_orders))) dataExchange.setMessage("Book selection failed: an error occurred.");
			// invoke method to get cart content
			xml_shoppingcart = service.view(id);
			// fills dataExchange variable
			dataExchange.setShoppingcart(xml_shoppingcart);
		} catch (Exception e) {
			dataExchange.setMessage("Book selection failed: an error occurred.");
			e.printStackTrace();
		}
		
		// forward request to shoppingcart page
		Utils.forwardToPage(Constants.JSP_CART, getServletContext(),
				request, response);
	}

}
