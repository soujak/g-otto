package g8.test;

import org.jboss.ejb3.annotation.Service;
import org.jboss.ejb3.cache.Optimized;

//@Clustered
@Service
public class Prova implements ProvaMBean, ProvaRemote, Optimized {

	public int counter;
	public boolean modified=false;
	public boolean master = false;
	
	public Prova() {
		this.counter = 0;
	}
	
	public int inc() {
		this.counter ++;
		this.modified = true;
		System.out.println("Prova: inc ["+this.counter+"]");
		return this.counter;
	}
	
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
	
	public boolean isMasterNode(){
		System.out.println(
				"Prova: i am "+
				(this.master ? "" : "not") +
				" the master node");
		return this.master;
	}
	public void startSingleton(){
		this.master=true;
		System.out.println("Prova: start singleton");
	}
	public void stopSingleton(){
		this.master=false;
		System.out.println("Prova: stop singleton");
	}

	@Override
	public boolean isModified() {
		return this.modified;
	}
}

