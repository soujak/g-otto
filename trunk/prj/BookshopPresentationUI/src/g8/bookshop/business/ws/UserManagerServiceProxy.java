package g8.bookshop.business.ws;

public class UserManagerServiceProxy implements g8.bookshop.business.ws.UserManagerService {
  private String _endpoint = null;
  private g8.bookshop.business.ws.UserManagerService userManagerService = null;
  
  public UserManagerServiceProxy() {
    _initUserManagerServiceProxy();
  }
  
  public UserManagerServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserManagerServiceProxy();
  }
  
  private void _initUserManagerServiceProxy() {
    try {
      userManagerService = (new g8.bookshop.business.ws.UserManagerServiceServiceLocator()).getUserManagerServicePort();
      if (userManagerService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userManagerService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userManagerService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userManagerService != null)
      ((javax.xml.rpc.Stub)userManagerService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public g8.bookshop.business.ws.UserManagerService getUserManagerService() {
    if (userManagerService == null)
      _initUserManagerServiceProxy();
    return userManagerService;
  }
  
  public boolean authenticate(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (userManagerService == null)
      _initUserManagerServiceProxy();
    return userManagerService.authenticate(arg0, arg1, arg2);
  }
  
  public boolean disconnect(java.lang.String arg0) throws java.rmi.RemoteException{
    if (userManagerService == null)
      _initUserManagerServiceProxy();
    return userManagerService.disconnect(arg0);
  }
  
  
}