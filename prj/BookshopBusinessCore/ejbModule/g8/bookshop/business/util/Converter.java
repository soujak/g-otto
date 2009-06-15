/**
 * 
 */
package g8.bookshop.business.util;

import g8.bookshop.business.core.OrderRemote;
import g8.bookshop.business.core.ShoppingCartRemote;
import g8.bookshop.persistence.Book;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author soujak
 *
 */

//FIXME: rivedimi, commentami, formattami ed espandimi.
@Stateless
public class Converter implements ConverterLocal, ConverterRemote {
	@PersistenceContext(unitName = "InformationManager")
	private EntityManager em;
	@Resource 
	private SessionContext sessionContext;
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * B O O K * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/**
	 * Transform Book object to XML element
	 * @param obj
	 * @return
	 */
	private Element bookToXML(Document document, Book book) {
		Element item = document.createElement("book");
		item.setAttribute("author", book.getAuthor() );
		item.setAttribute("title", book.getTitle());
		item.setAttribute("year", Integer.toString(book.getYear()));
		item.setAttribute("isbn", book.getISBN());
		item.setAttribute("editor",book.getEditor());
		item.setAttribute("id", Long.toString(book.getId()));
		return item;
	}
	
	/* (non-Javadoc)
	 * @see g8.bookshop.business.util.ConverterLocal#booksToXML(java.util.List)
	 */
	public String booksToXML(List<Book> books) 
	throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = document.createElement("books");
		for (Book book : books) {
			root.appendChild(bookToXML(document, book));			
		}
		document.appendChild(root);
		return xmlDocumentToString(document);
	}

	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * S H O P P I N G C A R T * * * * * * * * * * *  
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/**
	 * Transform order to XML element for ShoppingCart (stock)
	 * @param obj
	 * @return
	 */
	private Element orderToXML(Document document, OrderRemote order) {
		Element item = document.createElement("stock");
		item.setAttribute("quantity", Integer.toString(order.getQuantity()));
		item.appendChild(bookToXML(document, order.getBook()));
		return item;	
	}
	
	
	/* (non-Javadoc)
	 * @see g8.bookshop.business.util.ConverterLocal#shoppingCartToXML(g8.bookshop.business.core.ShoppingCartRemote)
	 */
	public String shoppingCartToXML(ShoppingCartRemote sc) 
	throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		List<OrderRemote> orders = sc.getOrders();
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = document.createElement("shoppingcart");
		for (OrderRemote order : orders) {
			root.appendChild(orderToXML(document, order));			
		}
		document.appendChild(root);
		return xmlDocumentToString(document);
	}
	
	
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * O R D E R * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/**
	 * Convert a XML node in a Order
	 * @param xmlNode the XML node
	 * @return the Order if the node is successfully converted, null otherwise 
	 */
	private OrderRemote xmlNodeToOrder(Node xmlNode)
	throws ParserConfigurationException, SAXException, IOException {
		int quantity = 0;
		String bookid = null;
		quantity = Integer.parseInt(xmlNode.getAttributes().getNamedItem("quantity").getNodeValue());
		bookid = xmlNode.getAttributes().getNamedItem("bookid").getNodeValue();
		Book b = em.find(Book.class, Long.parseLong(bookid));
		OrderRemote o = null;
		if(b != null) {
			o = (OrderRemote)sessionContext.lookup("BookshopBusiness/Order/remote");
		    o.setQuantity(quantity);
		    o.setBook(b);
		}
		return o;
	}
	
	/* (non-Javadoc)
	 * @see g8.bookshop.business.util.ConverterLocal#xmlToOrders(java.lang.String)
	 */
	public List<OrderRemote> xmlToOrders(String xml) throws ParserConfigurationException, SAXException, IOException {
		Document document = xmlDocumentCreator(xml);
		Element root = document.getDocumentElement();
		NodeList orders = root.getChildNodes();
		List<OrderRemote> result = new ArrayList<OrderRemote>();
		for (int i = 0; i < orders.getLength(); i++) {
			OrderRemote o = xmlNodeToOrder(orders.item(i));
			if(o != null)
				result.add(o);
		}
		return result;
	}
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * U T I L S * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	
	private Document xmlDocumentCreator(String xml)
	throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Reader reader = new StringReader(xml);
		InputSource inSource = new InputSource(reader);

		Document doc = db.parse(inSource);
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	private String xmlDocumentToString(Document document) 
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
