package g8.bookshop.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Entity implementation class Book
 * @author soujak
 */

@Entity
@Cache (usage=CacheConcurrencyStrategy.READ_ONLY)
//@Table(name = "Book", schema = "my09_10")
@NamedQueries({
    @NamedQuery(
            name="search",
            query="SELECT b FROM Book b WHERE " +
                    "b.Title = :Title " +
                    "OR b.Author = :Author " +
                    "OR b.Editor = :Editor " +
                    "OR b.ISBN = :ISBN " +
                    "OR b.Year = :Year")
})
public class Book implements Serializable {

	private String Title;
	private String Author;
	private int Year;
	private String Editor;
	private String ISBN;
	@Id @GeneratedValue
	private long id;
	private static final long serialVersionUID = 1L;

	public Book() {
		super();
	}
	
	public String getTitle() {
		return this.Title;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	
	public String getAuthor() {
		return this.Author;
	}
	public void setAuthor(String Author) {
		this.Author = Author;
	}
	
	public int getYear() {
		return this.Year;
	}

	public void setYear(int Year) {
		this.Year = Year;
	}
	
	public String getEditor() {
		return this.Editor;
	}
	public void setEditor(String Editor) {
		this.Editor = Editor;
	}
	
	public String getISBN() {
		return this.ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public long getId() {
		return id;
	}
	void setId(long id) {
		this.id = id;
	}
}
