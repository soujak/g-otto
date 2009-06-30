/**
 * UserManagerServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package g8.bookshop.business.ws;

public class UserManagerServiceServiceLocator extends org.apache.axis.client.Service implements g8.bookshop.business.ws.UserManagerServiceService {

    public UserManagerServiceServiceLocator() {
    }


    public UserManagerServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UserManagerServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UserManagerServicePort
    private java.lang.String UserManagerServicePort_address = "http://zaccaria.cs.unibo.it:8080/services/UserManagerService";

    public java.lang.String getUserManagerServicePortAddress() {
        return UserManagerServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UserManagerServicePortWSDDServiceName = "UserManagerServicePort";

    public java.lang.String getUserManagerServicePortWSDDServiceName() {
        return UserManagerServicePortWSDDServiceName;
    }

    public void setUserManagerServicePortWSDDServiceName(java.lang.String name) {
        UserManagerServicePortWSDDServiceName = name;
    }

    public g8.bookshop.business.ws.UserManagerService getUserManagerServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UserManagerServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUserManagerServicePort(endpoint);
    }

    public g8.bookshop.business.ws.UserManagerService getUserManagerServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            g8.bookshop.business.ws.UserManagerServiceBindingStub _stub = new g8.bookshop.business.ws.UserManagerServiceBindingStub(portAddress, this);
            _stub.setPortName(getUserManagerServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUserManagerServicePortEndpointAddress(java.lang.String address) {
        UserManagerServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (g8.bookshop.business.ws.UserManagerService.class.isAssignableFrom(serviceEndpointInterface)) {
                g8.bookshop.business.ws.UserManagerServiceBindingStub _stub = new g8.bookshop.business.ws.UserManagerServiceBindingStub(new java.net.URL(UserManagerServicePort_address), this);
                _stub.setPortName(getUserManagerServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("UserManagerServicePort".equals(inputPortName)) {
            return getUserManagerServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.business.bookshop.g8/", "UserManagerServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.business.bookshop.g8/", "UserManagerServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UserManagerServicePort".equals(portName)) {
            setUserManagerServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
