/**
 * ShoppingCartServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package g8.bookshop.business.ws;

public class ShoppingCartServiceServiceLocator extends org.apache.axis.client.Service implements g8.bookshop.business.ws.ShoppingCartServiceService {

    public ShoppingCartServiceServiceLocator() {
    }


    public ShoppingCartServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ShoppingCartServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ShoppingCartServicePort
    private java.lang.String ShoppingCartServicePort_address = "http://127.0.0.1:8080/BookshopBusiness-BookshopBusinessCore/ShoppingCartService";

    public java.lang.String getShoppingCartServicePortAddress() {
        return ShoppingCartServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ShoppingCartServicePortWSDDServiceName = "ShoppingCartServicePort";

    public java.lang.String getShoppingCartServicePortWSDDServiceName() {
        return ShoppingCartServicePortWSDDServiceName;
    }

    public void setShoppingCartServicePortWSDDServiceName(java.lang.String name) {
        ShoppingCartServicePortWSDDServiceName = name;
    }

    public g8.bookshop.business.ws.ShoppingCartService getShoppingCartServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ShoppingCartServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getShoppingCartServicePort(endpoint);
    }

    public g8.bookshop.business.ws.ShoppingCartService getShoppingCartServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            g8.bookshop.business.ws.ShoppingCartServiceBindingStub _stub = new g8.bookshop.business.ws.ShoppingCartServiceBindingStub(portAddress, this);
            _stub.setPortName(getShoppingCartServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setShoppingCartServicePortEndpointAddress(java.lang.String address) {
        ShoppingCartServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (g8.bookshop.business.ws.ShoppingCartService.class.isAssignableFrom(serviceEndpointInterface)) {
                g8.bookshop.business.ws.ShoppingCartServiceBindingStub _stub = new g8.bookshop.business.ws.ShoppingCartServiceBindingStub(new java.net.URL(ShoppingCartServicePort_address), this);
                _stub.setPortName(getShoppingCartServicePortWSDDServiceName());
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
        if ("ShoppingCartServicePort".equals(inputPortName)) {
            return getShoppingCartServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.business.bookshop.g8/", "ShoppingCartServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.business.bookshop.g8/", "ShoppingCartServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ShoppingCartServicePort".equals(portName)) {
            setShoppingCartServicePortEndpointAddress(address);
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