package g8.bookshop.business.core;

import g8.bookshop.persistence.Book;

import javax.ejb.Local;

@Local
public interface OrderLocal {
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
}