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
		String id = session.getId();
		
		// invoke UserManager service...
		boolean isAuthenticated = false;
		try {
			isAuthenticated = (new UserManagerServiceServiceLocator())
				.getUserManagerServicePort().authenticate(id, username, password);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		if(isAuthenticated) {
			dataExchange.setUsername(username);
			dataExchange.setAuthenticated(true);
			Utils.forwardToPage("/pages/home.jsp", getServletContext(), 
					request, response);
		} else {
			dataExchange.setUsername(DataExchange.GUESTNAME);
			dataExchange.setAuthenticated(false);
			Utils.forwardToPage("/pages/index.jsp", getServletContext(), 
					request, response);
		}
	}
}