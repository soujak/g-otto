package g8.bookshop.presentation.content.formatter;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

/**
 * An utility class that caches XSLT stylesheets in memory. For info on what
 * xsltName means see parameter name of Class.getResource()
 */
public class StylesheetCache {
	/*
	 * map xslt file names to MapEntry instances
	 */
	private static Map cache = new HashMap();

	/**
	 * Flush all cached stylesheets from memory, emptying the cache.
	 */
	public static synchronized void flushAll() {
		cache.clear();
	}

	/**
	 * Flush a specific cached stylesheet from memory.
	 * 
	 * @param xsltName
	 *            name of xslt to remove
	 */
	public static synchronized void flush(String xsltName) {
		cache.remove(xsltName);
	}

	/**
	 * Returns a new Transformer instance for the specified Xslt name. A new
	 * entry is added if this is the first request for the given Xslt.
	 * Otherwise, the already cached Xslt is returned. If the cached Xslt has
	 * been modified, it is reloaded.
	 * 
	 * @param xsltNam
	 *            name of an Xslt stylesheet.
	 * @return a transformation context for the given stylesheet.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static synchronized Transformer newTransformer(String xsltName)
			throws TransformerConfigurationException, IOException,
			URISyntaxException {

		FileLoader fl = new FileLoader();

		/**
		 * check whether the file has been modified
		 */
		long xslLastModified = fl.getLastModified(xsltName);
		MapEntry entry = (MapEntry) cache.get(xsltName);

		if (entry != null) {
			/**
			 * if so, remove entry (it will be reloaded later)
			 */
			if (xslLastModified > entry.lastModified) {
				entry = null;
			}
		}

		/**
		 * create a new entry in the cache if necessary
		 */
		if (entry == null) {
			Source xslSource = fl.getStreamSource(xsltName);

			TransformerFactory transFact = TransformerFactory.newInstance();
			Templates templates = transFact.newTemplates(xslSource);

			entry = new MapEntry(xslLastModified, templates);
			cache.put(xsltName, entry);
		}

		return entry.templates.newTransformer();
	}

	/**
	 * class can't be istantiated!
	 */
	private StylesheetCache() {
	}

	/**
	 * This class represents a value in the cache Map.
	 */
	static class MapEntry {
		long lastModified; /* represents when the file was last modified */
		Templates templates;

		MapEntry(long lastModified, Templates templates) {
			this.lastModified = lastModified;
			this.templates = templates;
		}
	}
}
