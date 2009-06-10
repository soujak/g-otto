package g8.bookshop.presentation.servlet;

import g8.bookshop.presentation.content.manager.DataExchange;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Utils class
 * @author francesco
 *
 */
public class Utils {
	
	public static void forwardToPage(
			String address,
			ServletContext context,
			HttpServletRequest request,
			HttpServletResponse response) {
		RequestDispatcher dispatcher = context.getRequestDispatcher(address);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.err.println("Servlet exception: Request forwarding error");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO exception: Request forwarding error");
			e.printStackTrace();
		}
	}
	
	public static DataExchange getDataExchange(HttpSession session) {
		DataExchange dataExchange;
		if (session.isNew()) dataExchange = new DataExchange();
		else dataExchange = (DataExchange)session.getAttribute("DataExchange");
		return dataExchange;
	}
	
}
