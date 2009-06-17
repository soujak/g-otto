package g8.test;

import org.jboss.ejb3.annotation.Management;

@Management
public interface ProvaMBean {
	int inc();
	
	void create() throws Exception;
	void start() throws Exception;
	void stop();
	void destroy();
	
	boolean isMasterNode();
	void startSingleton();
	void stopSingleton();
}