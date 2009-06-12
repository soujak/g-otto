/**
 * ShoppingCartService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package g8.bookshop.business.ws;

public interface ShoppingCartService extends java.rmi.Remote {
    public boolean addOrders(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public boolean checkOut(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean update(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String view(java.lang.String arg0) throws java.rmi.RemoteException;
}
