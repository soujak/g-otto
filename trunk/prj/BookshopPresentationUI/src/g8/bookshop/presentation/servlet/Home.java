package g8.bookshop.presentation.servlet;

import g8.bookshop.presentation.Constants;
import g8.bookshop.presentation.content.manager.DataExchange;
import g8.bookshop.business.ws.CatalogueServiceServiceLocator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    	String auth = (request.getParameter("auth") == null) ? null : "";
    	String last = (request.getParameter("last") == null) ? null : "";

    	try {    		
        	// re-run search to get last search results
        	String xml_booklist = "<books />";
        	if((last != null) && (!(dataExchange.getKey().equalsIgnoreCase("")))) {
        		xml_booklist = (new CatalogueServiceServiceLocator())
        			.getCatalogueServicePort().fullSearch(dataExchange.getKey());
        		dataExchange.setBooklist(xml_booklist);
        	}
        	
        	// initialize message string
        	dataExchange.setMessage("");
        	
    		// re-initialize books xml string
    		dataExchange.setBooklist(xml_booklist);
    		
    	} catch (Exception e) {
    		dataExchange.setMessage("An error occurred, please try refresh page.");
    		e.printStackTrace();
    	}
    	    	
		// dispatching requests to JSP pages...
    	String url = (dataExchange.getAuthenticated()) ? Constants.JSP_CUSTOMER_SEARCH : Constants.JSP_GUEST_SEARCH;
    	url = (auth != null) ? Constants.JSP_AUTH : url;
    	Utils.forwardToPage(url, getServletContext(),
    			request, response);
    }
}
