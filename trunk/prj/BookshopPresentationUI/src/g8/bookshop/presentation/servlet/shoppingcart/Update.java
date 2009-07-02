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
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		
		// initialize orders xml string
		String xml_orders = Constants.EMPTY_ORDERS;
		// initialize update result variable
		boolean updated = false;
		
		// initialize shopping cart xml string
		String xml_shoppingcart = Constants.EMPTY_CART;
		// initialize checkout result variable
		boolean payed = false;
		
		try {
			Enumeration params = request.getParameterNames();
			if(params.hasMoreElements()) {
				String param;
				Document document;
				Element root;
				Element item;
				
				// create DOMDocument
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				// create document element
				root = document.createElement("orders");
				document.appendChild(root);
				while(params.hasMoreElements()) {
					param = (String)params.nextElement();
					if(param.startsWith("book")) {
						item = document.createElement("order");
						// extracting bookid from request parameter
						// infact, the request paramer is composed by string "book" + bookID
						item.setAttribute("bookid", param.substring(4));
						item.setAttribute("quantity", request.getParameter(param));
						root.appendChild(item);
					}
				}
				xml_orders = Utils.xmlDocumentToString(document);
			}
			// initialize shoppingcart service client...
			ShoppingCartService service = (new ShoppingCartServiceServiceLocator()).getShoppingCartServicePort();			
			// updates cart
			updated = service.update(session.getId(), xml_orders);
			// retrieves updated order list
			xml_shoppingcart = service.view(session.getId());			
			dataExchange.setShoppingcart(xml_shoppingcart);
			// caching cart updated order list
			dataExchange.setXmlCartCache(xml_shoppingcart);

			if(request.getParameter("operation").equalsIgnoreCase("checkout"))
				payed = service.checkOut(session.getId());
			
		} catch (Exception e) {
			dataExchange.setMessage("Shoppingcart update failed: an error occurred.");
			e.printStackTrace();
		}
		
		if(updated) dataExchange.setMessage("Shopping cart updated.");
		else if(payed) dataExchange.setMessage("Shopping cart checked out.");
		
		Utils.forwardToPage(Constants.JSP_CART, getServletContext(),
				request, response);
	}
}