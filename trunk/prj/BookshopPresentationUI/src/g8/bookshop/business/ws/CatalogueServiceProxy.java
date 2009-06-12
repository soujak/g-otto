package g8.bookshop.business.ws;

public class CatalogueServiceProxy implements g8.bookshop.business.ws.CatalogueService {
  private String _endpoint = null;
  private g8.bookshop.business.ws.CatalogueService catalogueService = null;
  
  public CatalogueServiceProxy() {
    _initCatalogueServiceProxy();
  }
  
  public CatalogueServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCatalogueServiceProxy();
  }
  
  private void _initCatalogueServiceProxy() {
    try {
      catalogueService = (new g8.bookshop.business.ws.CatalogueServiceServiceLocator()).getCatalogueServicePort();
      if (catalogueService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)catalogueService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)catalogueService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (catalogueService != null)
      ((javax.xml.rpc.Stub)catalogueService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public g8.bookshop.business.ws.CatalogueService getCatalogueService() {
    if (catalogueService == null)
      _initCatalogueServiceProxy();
    return catalogueService;
  }
  
  public java.lang.String fullSearch(java.lang.String arg0) throws java.rmi.RemoteException{
    if (catalogueService == null)
      _initCatalogueServiceProxy();
    return catalogueService.fullSearch(arg0);
  }
  
  public java.lang.String search(java.lang.String arg0) throws java.rmi.RemoteException{
    if (catalogueService == null)
      _initCatalogueServiceProxy();
    return catalogueService.search(arg0);
  }
  
  
}