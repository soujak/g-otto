/**
 * UserManagerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package g8.bookshop.business.ws;

public interface UserManagerService extends java.rmi.Remote {
    public boolean authenticate(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public boolean disconnect(java.lang.String arg0) throws java.rmi.RemoteException;
}
