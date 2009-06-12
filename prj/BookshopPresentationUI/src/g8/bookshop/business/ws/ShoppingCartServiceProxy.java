package g8.bookshop.business.ws;

public class ShoppingCartServiceProxy implements g8.bookshop.business.ws.ShoppingCartService {
  private String _endpoint = null;
  private g8.bookshop.business.ws.ShoppingCartService shoppingCartService = null;
  
  public ShoppingCartServiceProxy() {
    _initShoppingCartServiceProxy();
  }
  
  public ShoppingCartServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initShoppingCartServiceProxy();
  }
  
  private void _initShoppingCartServiceProxy() {
    try {
      shoppingCartService = (new g8.bookshop.business.ws.ShoppingCartServiceServiceLocator()).getShoppingCartServicePort();
      if (shoppingCartService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)shoppingCartService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)shoppingCartService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (shoppingCartService != null)
      ((javax.xml.rpc.Stub)shoppingCartService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public g8.bookshop.business.ws.ShoppingCartService getShoppingCartService() {
    if (shoppingCartService == null)
      _initShoppingCartServiceProxy();
    return shoppingCartService;
  }
  
  public boolean addOrders(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (shoppingCartService == null)
      _initShoppingCartServiceProxy();
    return shoppingCartService.addOrders(arg0, arg1);
  }
  
  public boolean checkOut(java.lang.String arg0) throws java.rmi.RemoteException{
    if (shoppingCartService == null)
      _initShoppingCartServiceProxy();
    return shoppingCartService.checkOut(arg0);
  }
  
  public boolean update(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (shoppingCartService == null)
      _initShoppingCartServiceProxy();
    return shoppingCartService.update(arg0, arg1);
  }
  
  public java.lang.String view(java.lang.String arg0) throws java.rmi.RemoteException{
    if (shoppingCartService == null)
      _initShoppingCartServiceProxy();
    return shoppingCartService.view(arg0);
  }
  
  
}