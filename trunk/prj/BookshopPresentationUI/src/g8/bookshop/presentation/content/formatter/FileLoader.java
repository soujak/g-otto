package g8.bookshop.presentation.content.formatter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.transform.stream.StreamSource;

public class FileLoader {

	public StreamSource getStreamSource(String string) throws IOException {
		return new StreamSource(getInputStream(string));
	}

	public InputStream getInputStream(String string) throws IOException {
		URL url = getURL(string);
		InputStream in = url.openStream();
		if (in == null) {
			throw new RuntimeException("Can't find " + string);
		}
		return in;
	}

	public long getLastModified(String string) throws URISyntaxException {
		URL url = getURL(string);
		File file = new File(string);
		return file.lastModified();
	}

	private URL getURL(String string) {
		URL url = this.getClass().getResource(string);
		if ((url == null) || (url.toString().length() == 0)) {
			throw new RuntimeException("Can't get a valid url for " + string);
		}
		return url;
	}

}
