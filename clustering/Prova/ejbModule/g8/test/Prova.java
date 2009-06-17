package g8.test;

import org.jboss.ejb3.annotation.Service;

@Service
public class Prova implements ProvaRemote 
{

	public int counter;
	
	public Prova() {
		this.counter = 0;
	}
	
	public int inc() {
		this.counter ++;
		System.out.println("Prova: inc ["+this.counter+"]");
		return this.counter;
	}
}

