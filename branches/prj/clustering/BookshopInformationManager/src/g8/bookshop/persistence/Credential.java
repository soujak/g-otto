package g8.bookshop.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Entity implementation class Credential
 * @author soujak
 */
@Entity
@Cache (usage=CacheConcurrencyStrategy.READ_ONLY)
public class Credential implements Serializable {

	   
	@Id
	private String Name;
	private String Password;
	private static final long serialVersionUID = 1L;

	public Credential() {
		super();
	}   
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}   
	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}
   
}
