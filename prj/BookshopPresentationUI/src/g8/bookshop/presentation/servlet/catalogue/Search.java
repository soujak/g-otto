package g8.bookshop.presentation.servlet.catalogue;

import g8.bookshop.presentation.content.manager.DataExchange;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class HelloServlet
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataExchange dataExchange = new DataExchange();
		dataExchange.setKey(request.getParameter("key").toString());
				
		// richiesta al web service dei risultati della ricerca
		// tramite il valore key
		
		dataExchange.setBooklist("");
		dataExchange.setUsername("guest");
		
		
		request.getSession().setAttribute("DataExchange", dataExchange);
		forwardToPage("/pages/index.jsp", request, response);
	}
	
	private void forwardToPage(String address, HttpServletRequest request, HttpServletResponse response) 
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
