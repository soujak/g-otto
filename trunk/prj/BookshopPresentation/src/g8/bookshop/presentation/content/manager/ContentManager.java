package g8.bookshop.presentation.content.manager;

public class ContentManager {
	
	private String booklist, username;

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
