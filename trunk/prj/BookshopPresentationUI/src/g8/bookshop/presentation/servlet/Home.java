package g8.bookshop.presentation.servlet;

import g8.bookshop.presentation.Constants;
import g8.bookshop.presentation.content.manager.DataExchange;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// retrieves user session...
    	HttpSession session = request.getSession();
    	// retrieves DataExchage user instance...
    	DataExchange dataExchange = Utils.getDataExchange(session);
    	// retrieves dispatcher parameter...
    	String auth = (request.getParameter("auth") == null) ? "" : request.getParameter("auth");

    	try {
    		// re-initialize books xml string
    		dataExchange.setBooklist("<books />");
    		// initialize message string
    		dataExchange.setMessage("");
    	} catch (ParserConfigurationException e) {
    		e.printStackTrace();
    	} catch (SAXException e) {
    		e.printStackTrace();
    	}
    	
		// dispatching requests to JSP pages...
    	String url = (dataExchange.getAuthenticated()) ? Constants.JSP_CUSTOMER_SEARCH : Constants.JSP_GUEST_SEARCH;
    	url = (auth.equalsIgnoreCase("true")) ? Constants.JSP_AUTH : url;
    	Utils.forwardToPage(url, getServletContext(),
    			request, response);
    }
}
