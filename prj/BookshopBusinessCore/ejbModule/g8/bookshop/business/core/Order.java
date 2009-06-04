/**
 * 
 */
package g8.bookshop.business.core;

/**
 * @author soujak
 *
 */
public class Order implements OrderLocal {
	private BookLocal book;
	private int quantity;
	
	/**
	 * @return the book
	 */
	BookLocal getBook() {
		return book;
	}
	/**
	 * @return the quantity
	 */
	int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity
	 */
	void setQuantity(int q) {
		this.quantity = q; 
	}
	
	/**
	 * @param b
	 * @param q
	 */
	Order(BookLocal b, int q) {
		super();
		this.book = b;
		this.quantity = q;
	}
	
}
