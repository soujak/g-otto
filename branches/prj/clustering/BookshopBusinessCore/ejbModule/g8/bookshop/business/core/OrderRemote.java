package g8.bookshop.business.core;

import g8.bookshop.persistence.Book;

import javax.ejb.Remote;

/**
 * TODO javadoc
 */
@Remote
public interface OrderRemote {
	/**
	 * @return the book
	 */
	Book getBook();

	/**
	 * @return the quantity
	 */
	int getQuantity();
	
	/**
	 * @param quantity
	 */
	void setQuantity(int q);
	
	/**
	 * @param b
	 * 			the book to set
	 * 
	 */
	public void setBook(Book b);
}