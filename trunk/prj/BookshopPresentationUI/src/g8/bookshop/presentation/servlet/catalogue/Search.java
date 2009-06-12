package g8.bookshop.presentation.servlet.catalogue;

import g8.bookshop.business.ws.CatalogueService;
import g8.bookshop.business.ws.CatalogueServiceService;
import g8.bookshop.business.ws.CatalogueServiceServiceLocator;
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
import javax.xml.ws.WebServiceRef;

import org.xml.sax.SAXException;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// initialize result variable
		String result = "<books />";
		// retrieve search parameter
		String key = request.getParameter("key").toString();
		// retrieves user session...
		HttpSession session = request.getSession();
		// retrieves DataExchage user instance...
		DataExchange dataExchange = Utils.getDataExchange(session);
		
		// invoke catalogue web service
		;
		try {
			(new CatalogueServiceServiceLocator()).getCatalogueServicePort().fullSearch(key);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// filling dataExchange instance fields
		dataExchange.setKey(key);
		
		try {
			dataExchange.setBooklist(result);
		} catch (ParserConfigurationException e) {
			System.err.println("Parser configuration error");
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("XML parser error");
			e.printStackTrace();
		}
	
		session.setAttribute("DataExchange", dataExchange);
		String url = (dataExchange.getAuthenticated()) ? "/pages/home.jsp" : "/pages/index.jsp";
		Utils.forwardToPage(url, getServletContext(),
				request, response);
	}
}
