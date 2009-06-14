package g8.bookshop.presentation.servlet;

import g8.bookshop.presentation.content.manager.DataExchange;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dispatcher() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieves user session...
		HttpSession session = request.getSession();
		// retrieves DataExchage user instance...
		DataExchange dataExchange = Utils.getDataExchange(session);
		
		// dispatches requests
		String url = (dataExchange.getAuthenticated()) ? "/pages/customer_search.jsp" : "/pages/guest_search.jsp";
		Utils.forwardToPage(url, getServletContext(), request, response);
	}

}
