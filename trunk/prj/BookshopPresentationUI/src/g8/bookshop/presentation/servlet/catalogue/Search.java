package g8.bookshop.presentation.servlet.catalogue;

import java.io.IOException;

import g8.bookshop.presentation.content.manager.ContentManager;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String out = "It Works!";
		
		// richiesta al web service dei risultati della ricerca
		
		ContentManager contentManager = new ContentManager();
		contentManager.setBooklist(out);
		contentManager.setUsername("foo");
		
		request.getSession().setAttribute("ContentManager", contentManager);
		forwardToPage("/pages/index.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
