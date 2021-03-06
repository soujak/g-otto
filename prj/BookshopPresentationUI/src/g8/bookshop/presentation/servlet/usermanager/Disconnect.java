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
		// retrieves user session
		HttpSession session = request.getSession();
		// gets session ID
		String id = session.getId();
		
		// initialize disconnection result variable
		boolean disconnected = false;

		try {
			// destroy user session
			session.invalidate();
			// creates new user session
			session = request.getSession();
			// create and put new dataExchange instance into user session data
			DataExchange dataExchange = Utils.getDataExchange(session);
			
			// invoke UserManager service to stop authenticated session
			disconnected = (new UserManagerServiceServiceLocator())
				.getUserManagerServicePort().disconnect(id);
			
			if(disconnected)dataExchange.setMessage("Disconnection succeeded: all session data has been removed correctly.");
			else dataExchange.setMessage("Disconnection forced: an error occurred but all session data has been removed correctly.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// forward request to search page
		Utils.forwardToPage(Constants.JSP_GUEST_SEARCH, getServletContext(), 
				request, response);	
	}
}
