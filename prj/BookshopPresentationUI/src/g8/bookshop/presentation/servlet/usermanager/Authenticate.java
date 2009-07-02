package g8.bookshop.presentation.servlet.usermanager;

import g8.bookshop.business.ws.CatalogueServiceServiceLocator;
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

/**
 * Servlet implementation class Authenticate
 */
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// retrieves user session...
		HttpSession session = request.getSession();
		
		// retrieves DataExchage user instance...
		DataExchange dataExchange = Utils.getDataExchange(session);
		
		// retrieves parameter to evaluate post request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// retrieves session ID
		String id = session.getId();
				
		// initialize message string
		dataExchange.setMessage("");
		
		// initialize authentication result variable
		boolean authenticated = false;
				
		try {
			if(!(dataExchange.getAuthenticated())) {
				// invoke UserManager service to authenticates user session
				authenticated = (new UserManagerServiceServiceLocator())
				.getUserManagerServicePort().authenticate(id, username, password);

				// fills dataExchange variable and forward request to search page
				if(authenticated) {
					dataExchange.setUsername(username);
					dataExchange.setMessage("Authentication succeeded.");
					
				} else {
					dataExchange.setUsername(Constants.GUEST_NAME);
					dataExchange.setResultsMessage("");
					dataExchange.setMessage("Authentication failed: invalid password or username.");
				}

				dataExchange.setAuthenticated(authenticated);
				dataExchange.setBooklist(dataExchange.getXmlBooklistCache());
			}

		} catch (Exception e) {
			dataExchange.setUsername(Constants.GUEST_NAME);
			dataExchange.setResultsMessage("");
			dataExchange.setMessage("Authentication failed: an error occurred.");
			e.printStackTrace();
		}

		
		// forwards request to search page
		String url = (dataExchange.getAuthenticated()) ? Constants.JSP_CUSTOMER_SEARCH : Constants.JSP_GUEST_SEARCH; 
		Utils.forwardToPage(url, getServletContext(), 
				request, response);
	}
}