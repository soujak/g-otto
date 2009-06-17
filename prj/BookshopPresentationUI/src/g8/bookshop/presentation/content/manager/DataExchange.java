package g8.bookshop.presentation.content.manager;

import g8.bookshop.presentation.Constants;
import g8.bookshop.presentation.content.formatter.XsltTransformer;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;



public class DataExchange {
	
	// fields...
	private String username = Constants.GUEST_NAME;
	private boolean authenticated = false;
	private String booklist = "";
	private String key = "";
	private String shoppingcart = "";
	private String message = "";
	private String resultsMessage = "";

	public String getResultsMessage() {
		return resultsMessage;
	}

	public void setResultsMessage(String resultsMessage) {
		this.resultsMessage = resultsMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShoppingcart() {
		return shoppingcart;
	}

	public void setShoppingcart(String xml_shoppingcart) throws ParserConfigurationException, SAXException {
		
		// initialize parameters struct
		String[] params = new String[2];
		// fills parameters struct
		params[0] = "mode:cart";
		params[1] = "form_action:Update";
		
		
		// initialize transformer
		XsltTransformer trans = new XsltTransformer();
		
		// initialize output variable
		String html_shoppingcart = "";
		
		// transform...
		try {
			html_shoppingcart = trans.transform(xml_shoppingcart, Constants.XSLT_FORMATTER, params);
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		this.shoppingcart = html_shoppingcart;
	}

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
		
		// initialize parameters struct
		String[] params = (this.authenticated) ? new String[3] : new String[2];
		// fills parameters struct
		params[0] = "mode:search";
		if(this.authenticated) {
			params[1] = "search_type:authenticated";
			params[2] = "form_action:AddOrders";
		} else  params[1] = "search_type:simple";
		
		
		// initialize transformer
		XsltTransformer trans = new XsltTransformer();
		
		// initialize output variable
		String html_booklist = "";
		
		// transform...
		try {
			html_booklist = trans.transform(xml_booklist, Constants.XSLT_FORMATTER, params);
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		this.booklist = html_booklist;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
