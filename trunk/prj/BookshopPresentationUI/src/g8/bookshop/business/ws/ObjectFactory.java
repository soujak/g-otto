
package g8.bookshop.business.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the g8.bookshop.business.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Search_QNAME = new QName("http://ws.business.bookshop.g8/", "Search");
    private final static QName _FullSearch_QNAME = new QName("http://ws.business.bookshop.g8/", "FullSearch");
    private final static QName _FullSearchResponse_QNAME = new QName("http://ws.business.bookshop.g8/", "FullSearchResponse");
    private final static QName _SearchResponse_QNAME = new QName("http://ws.business.bookshop.g8/", "SearchResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: g8.bookshop.business.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link FullSearchResponse }
     * 
     */
    public FullSearchResponse createFullSearchResponse() {
        return new FullSearchResponse();
    }

    /**
     * Create an instance of {@link FullSearch }
     * 
     */
    public FullSearch createFullSearch() {
        return new FullSearch();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Search }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.business.bookshop.g8/", name = "Search")
    public JAXBElement<Search> createSearch(Search value) {
        return new JAXBElement<Search>(_Search_QNAME, Search.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FullSearch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.business.bookshop.g8/", name = "FullSearch")
    public JAXBElement<FullSearch> createFullSearch(FullSearch value) {
        return new JAXBElement<FullSearch>(_FullSearch_QNAME, FullSearch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FullSearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.business.bookshop.g8/", name = "FullSearchResponse")
    public JAXBElement<FullSearchResponse> createFullSearchResponse(FullSearchResponse value) {
        return new JAXBElement<FullSearchResponse>(_FullSearchResponse_QNAME, FullSearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.business.bookshop.g8/", name = "SearchResponse")
    public JAXBElement<SearchResponse> createSearchResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchResponse_QNAME, SearchResponse.class, null, value);
    }

}
