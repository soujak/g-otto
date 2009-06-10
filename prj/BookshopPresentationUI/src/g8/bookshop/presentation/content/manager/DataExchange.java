package g8.bookshop.presentation.content.manager;

import g8.bookshop.presentation.content.formatter.XsltTransformer;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;



public class DataExchange {
	
	public static final String GUESTNAME = "Guest";

	private String booklist = "";
	private String username = DataExchange.GUESTNAME;
	private String key = "";
	private String xslt_location = "formatter.xsl";
	private boolean authenticated = false;

	public boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBooklist() {
		return booklist;
	}

	public void setBooklist(String xml_booklist)
			throws ParserConfigurationException, SAXException {

		XsltTransformer trans = new XsltTransformer();
		String booklist_html = "";
		try {
			booklist_html = trans.transform(xml_booklist, xslt_location);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.booklist = booklist_html;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
