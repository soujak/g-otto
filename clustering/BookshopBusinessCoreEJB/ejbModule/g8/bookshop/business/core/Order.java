/**
 * 
 */
package g8.bookshop.business.core;

import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.Clustered;

import g8.bookshop.persistence.Book;

/**
 * @author soujak
 *
 */
@Clustered
@Stateful
public class Order implements OrderLocal, OrderRemote {
	private Book book;
	private int quantity;
	
	/**
	 * Constructor
	 * @param b
	 * @param q
	 */
	Order(Book b, int q) {
		super();
		this.book = b;
		this.quantity = q;
	}
	
	/**
	 * Minimal constructor
	 */
	public Order() {
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param q
	 * 			the quantity to set
	 * 
	 */
	public void setQuantity(int q) {
		this.quantity = q; 
	}

	/**
	 * @param b
	 * 			the book to set
	 * 
	 */
	public void setBook(Book b) {
		this.book = b;
	}
	
}
