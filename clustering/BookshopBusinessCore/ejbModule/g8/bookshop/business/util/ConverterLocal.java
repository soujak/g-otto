package g8.bookshop.business.util;

import g8.bookshop.business.core.OrderRemote;
import g8.bookshop.business.core.ShoppingCartRemote;
import g8.bookshop.persistence.Book;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

@Local
public interface ConverterLocal {

	/**
	 * Transform Book list to XML element
	 * @param books
	 * @return
	 * @throws ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public abstract String booksToXML(List<Book> books)
			throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException;

	/**
	 * Transform ShoppingCart object (order list) to XML element
	 * @param sc
	 * @return
	 * @throws ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public abstract String shoppingCartToXML(ShoppingCartRemote sc)
			throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException;

	public abstract List<OrderRemote> xmlToOrders(String xml)
			throws ParserConfigurationException, SAXException, IOException;

}