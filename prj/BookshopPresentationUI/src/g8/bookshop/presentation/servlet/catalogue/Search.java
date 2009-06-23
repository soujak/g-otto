package g8.bookshop.presentation.servlet.catalogue;

import g8.bookshop.business.ws.CatalogueServiceServiceLocator;
import g8.bookshop.presentation.Constants;
import g8.bookshop.presentation.content.manager.DataExchange;
import g8.bookshop.presentation.servlet.Utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.Document;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
	}

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		// retrieves user session...
		HttpSession session = request.getSession();
		// retrieves DataExchage user instance...
		DataExchange dataExchange = Utils.getDataExchange(session);
		
		// initialize result variable
		String xml_booklist = "<books />";
		
		// initialize message string
		dataExchange.setMessage("");
		
		// initialize results message string
		dataExchange.setResultsMessage("");
		
		try {
			
			// if search key is not null or empty... 
			// invoke catalogue full-text search service method
			if ((!(request.getParameter("key") == null)) && (!(request.getParameter("key").equalsIgnoreCase("")))) {
				xml_booklist = (new CatalogueServiceServiceLocator())
					.getCatalogueServicePort().fullSearch(request.getParameter("key"));
	
				// build results message
				Document books = Utils.xmlStringToDocument(xml_booklist);
				if(!(books.getDocumentElement().getChildNodes().getLength() == 0)) dataExchange.setResultsMessage("Results for '" + request.getParameter("key") + "'");
				else dataExchange.setResultsMessage("No results for '" + request.getParameter("key") + "'");
			}
			// fills dataExchange instance fields
			dataExchange.setKey(request.getParameter("key"));
			dataExchange.setBooklist(xml_booklist);
			
		} catch (Exception e) {
			dataExchange.setMessage("Search failed: an error occurred.");
			e.printStackTrace();
		}
	
		// forward request to search page
		String url = (dataExchange.getAuthenticated()) ? Constants.JSP_CUSTOMER_SEARCH : Constants.JSP_GUEST_SEARCH;
		Utils.forwardToPage(url, getServletContext(),
				request, response);
	}
}