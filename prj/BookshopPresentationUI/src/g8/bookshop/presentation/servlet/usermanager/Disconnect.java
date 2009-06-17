package g8.bookshop.presentation.servlet.usermanager;

import g8.bookshop.business.ws.UserManagerServiceServiceLocator;
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
 * Servlet implementation class Disconnect
 */
public class Disconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Disconnect() {
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
		// retrieves session ID...
		String id = session.getId();
		
		// initialize message string
		dataExchange.setMessage("");
		
		// initialize disconnection result variable
		boolean disconnected = false;

		try {
			// invoke UserManager service to stop authenticated session
			disconnected = (new UserManagerServiceServiceLocator())
				.getUserManagerServicePort().disconnect(id);

			// if disconnected re-initialize dataExchange variable
			if(disconnected) {
				dataExchange.setMessage("Disconnection succeeded: every session data has been deleted.");
				dataExchange.setAuthenticated(false);
				dataExchange.setUsername(Constants.GUEST_NAME);
				dataExchange.setKey(null);
				dataExchange.setBooklist("<books />");
				dataExchange.setShoppingcart("<shoppingcart />");
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		// forward request to search page
		Utils.forwardToPage(Constants.JSP_GUEST_SEARCH, getServletContext(), 
				request, response);	
	}
}
