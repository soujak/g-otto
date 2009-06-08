package g8.bookshop.presentation.content.manager;

public class DataExchange {

	private String booklist = "";
	private String username = "";
	private String key = "";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBooklist() {
		return booklist;
	}

	public void setBooklist(String booklist) {
		// qui va la trasformazione xslt. E la validazione?
		this.booklist = booklist;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
