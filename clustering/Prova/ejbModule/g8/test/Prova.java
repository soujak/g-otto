package g8.test;

import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.Service;
import org.jboss.jmx.adaptor.rmi.RMIAdaptor;

public class Prova implements ProvaMBean, ProvaRemote {

	public int counter;
//	public boolean modified=false;
	private boolean isMasterNode;
	
	public Prova() {
//		super();
		this.isMasterNode = false;
		this.counter = 0;
	}
	
	public int inc() {
		int ret = -1;
		System.out.println("Prova: am i the master node? "+isMasterNode());
		if (this.isMasterNode()) {
			System.out.println("Prova: inc ["+this.counter+"]");
			ret = ++counter;
//			this.modified = true;
		} else {
			String masterNode = this.getMasterNode();
			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.setProperty(Context.PROVIDER_URL, masterNode);
			props.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
			
			try {
				Context ctx = new InitialContext(props);
				RMIAdaptor adaptor = (RMIAdaptor) ctx.lookup("jmx/rmi/RMIAdaptor");
				ObjectName name = new ObjectName("g8.test:service=Prova");
				if (adaptor.isRegistered(name)) {
					System.out.println("Prova: invoking inc on " + masterNode);
					ret = ((Integer) adaptor.invoke(name, "inc", null, null)).intValue();
				}
			} catch (NamingException e) {
					e.printStackTrace();
				} catch (MalformedObjectNameException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				e.printStackTrace();
			} catch (MBeanException e) {
				e.printStackTrace();
			} catch (ReflectionException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		}
		return ret;
	}
	
//	public int inc() {
//		int ret = -1;
//		if (this.isMasterNode()) {
//			System.out.println("Prova ("+
//					this.getHAPartition().getClusterNode().getIpAddress().getHostAddress()+
//					"): inc ["+this.counter+"]");
//			ret = ++counter;
////			this.modified = true;
//		} else {
//			Properties env = new Properties();
//			String masterNode = this.getMasterSocket();
//			env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
//					"org.jnp.interfaces.NamingContextFactory");
//			env.setProperty(Context.PROVIDER_URL, masterNode);
//			env.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
//			ProvaRemote masterInstance;
//			try {
//				Context ctx = new InitialContext(env);
//				masterInstance = (ProvaRemote) ctx.lookup("ProvaEAR/Prova/remote");
//				ret = masterInstance.inc();
//				System.out.println("Prova ("+
//						this.getHAPartition().getClusterNode().getIpAddress().getHostAddress()+
//				"): inc: => " + masterNode);
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
//		return ret;
//	}

//	public void startService() throws Exception {  
//		System.out.println("Prova: start service");  
//		super.startService();  
//	}  

//	public void stopService() throws Exception {  
//		System.out.println("Prova: stop service");  
//		super.stopService();  
//	}  

	public boolean isMasterNode() {  
//		return super.isMasterNode();
		return this.isMasterNode;
	}  


	public void startSingleton() {
		this.isMasterNode=true;
		System.out.println("Prova: start singleton on node");
//				this.getHAPartition().getClusterNode().getIpAddress().getHostAddress()  
	}  


	public void stopSingleton() {
		this.isMasterNode=false;
		System.out.println("Prova: stop singleton on node");
//				this.getHAPartition().getClusterNode().getIpAddress().getHostAddress()
	}  

//	public void partitionTopologyChanged(List newReplicants, int newViewID) {
//		System.out.println("Prova: topology changed ");
////		super.partitionTopologyChanged(newReplicants, newViewID);  
//	}
	
//	public void setHAPartition(HAPartition partition)
//    {
//        System.out.println("set HAPartition: " + partition);
//        super.setHAPartition(partition);
//    }

	public void create() throws Exception {
		System.out.println("Prova: create");
	}
	public void start() throws Exception {
		System.out.println("Prova: start");
	}
	public void stop(){
		System.out.println("Prova: stop");
	}
	public void destroy(){
		System.out.println("Prova: destroy");
	}
	
	private String getMasterNode() {
		String masterNode = null;
		System.out.println("Prova: who is the master node?");
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		props.setProperty("jnp.partitionName", "G8Business");
		props.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		try {
			Context ctx = new InitialContext(props);
			RMIAdaptor adaptor = (RMIAdaptor) ctx.lookup("jmx/rmi/RMIAdaptor");
			ObjectName name = new ObjectName("jboss:service=HAPartition,partition=G8Business");
			if (adaptor.isRegistered(name)) {
				masterNode = ((Vector) adaptor.getAttribute(name, "CurrentView")).get(0).toString();
				System.out.println("Prova: master node is " + masterNode);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return masterNode;
	}
	
//	private String getMasterSocket() {
//		HAPartition partition = this.getHAPartition();
//		String ret = null;
//		if (partition != null) {
//			if (partition.getCurrentView() != null) {
//				ret = partition.getCurrentView().get(0).toString();
//			}  
//		}
//		return ret;
//	}
}
