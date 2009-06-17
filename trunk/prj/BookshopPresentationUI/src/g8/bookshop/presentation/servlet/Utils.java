package g8.bookshop.presentation.servlet;

import g8.bookshop.presentation.content.manager.DataExchange;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

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
	
	public static String xmlDocumentToString(Document document) 
	throws TransformerFactoryConfigurationError, TransformerException {
		// transform Document to String
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(document);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
		return writer.toString();
	}
}
