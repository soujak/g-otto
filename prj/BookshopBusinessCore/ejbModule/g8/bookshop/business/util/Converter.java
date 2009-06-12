/**
 * 
 */
package g8.bookshop.business.util;

import g8.bookshop.business.core.Order;
import g8.bookshop.business.core.ShoppingCartRemote;
import g8.bookshop.persistence.Book;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author soujak
 *
 */

//FIXME: rivedimi, commentami, formattami ed espandimi.
public class Converter {
	
	/**
	 * Transform Book object to Element XML
	 * @param obj
	 * @return
	 */
	private static Element bookToXML(Document document, Book book) {
		Element item = document.createElement("book");
		item.setAttribute("author", book.getAuthor() );
		item.setAttribute("title", book.getTitle());
		item.setAttribute("year", Integer.toString(book.getYear()));
		item.setAttribute("isbn", book.getISBN());
		item.setAttribute("editor",book.getEditor());
		item.setAttribute("id", Long.toString(book.getId()));
		return item;
	}
	
	public static String toXML(List<Book> books) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = document.createElement("books");
		for (Book book : books) {
			root.appendChild(bookToXML(document, book));			
		}
		document.appendChild(root);
		return XMLDocumentToString(document);
	}
	
	
	/**
	 * Transform Order object to Element XML
	 * @param obj
	 * @return
	 */
	private static Element orderToXML(Document document, Order order) {
		Element item = document.createElement("order");
		item.setAttribute("bookID", Long.toString(order.getBook().getId()));
		item.setAttribute("quantity", Integer.toString(order.getQuantity()));
		return item;	
	}
	
	
	
	private static String XMLDocumentToString(Document document) 
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

	public static String toXML(ShoppingCartRemote sc) {
		// TODO
		return null;
	}

	public static List<Order> toOrders(String ords) {
		// TODO
		return null;
	}

}
