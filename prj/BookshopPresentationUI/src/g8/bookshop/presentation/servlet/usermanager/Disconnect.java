package g8.bookshop.presentation.servlet.usermanager;

import g8.bookshop.business.ws.UserManagerServiceServiceLocator;
import g8.bookshop.presentation.content.manager.DataExchange;
import g8.bookshop.presentation.servlet.Utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

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

		// invoke UserManager service...
		try {
			// ... to stop authenticated session
			(new UserManagerServiceServiceLocator())
				.getUserManagerServicePort().disconnect(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		// fills dataExchange variable
		dataExchange.setUsername(DataExchange.GUESTNAME);
		dataExchange.setAuthenticated(false);
		// forward request to search page
		Utils.forwardToPage("/pages/guest_search.jsp", getServletContext(), 
				request, response);	
	}
}