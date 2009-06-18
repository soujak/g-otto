package g8.bookshop.presentation.content.formatter;

import g8.bookshop.presentation.servlet.Utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XsltTransformer {

	/**
	 * 
	 * @param xml
	 *            "whole xml", body not filename
	 * @param xslt_name
	 *            xslt name as in Class.getResource()
	 * @return
	 * @throws TransformerException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public String transform(String xml, String xslt_name, String[] params)
			throws TransformerException, IOException, URISyntaxException,
			ParserConfigurationException, SAXException {


		// retrieves xslt transformer... 
		Transformer transformer = StylesheetCache.newTransformer(xslt_name);
		
		// puts transformation parameter into transformer
		// splitted[0] : key
		// splitted[1] : value
		for(int i = 0; i < params.length; i++)
			transformer.setParameter(params[i].split(":")[0], params[i].split(":")[1]);
		
		/**
		 * preparing output writer
		 */
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);

		DOMSource source = transformToDOMSource(xml);

		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		//transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "file.dtd");

		transformer.transform(source, result);
		return sw.toString();
	}

	/**
	 * 
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private DOMSource transformToDOMSource(String xml)
			throws ParserConfigurationException, SAXException, IOException {
		Document doc = Utils.xmlStringToDocument(xml);
		DOMSource source = new DOMSource(doc);
		return source;
	}

}
