package g8.bookshop.business.util;

public class PrepareSQLStatement {

	/**
	 * Prepare a SQL statement for a book full search. 
	 * @param s string to search
	 * @return the SQL statement
	 */
	public static String prepareFullSearchSQL(String s) {
    	String[] strings = s.split(" ");
    	String SQLStatement = "SELECT b FROM Book b ";
    	if(strings.length > 0) {
    		SQLStatement += " WHERE b.Title LIKE '%" + strings[0] + "%'";
			SQLStatement += " OR b.Author LIKE '%" + strings[0] + "%'";
			SQLStatement += " OR b.Editor LIKE '%" + strings[0] + "%'";
			SQLStatement += " OR b.ISBN LIKE '%" + strings[0] + "%'";
			try {
				Integer year = Integer.parseInt(SQLStatement);
				SQLStatement += " OR b.Year = " + year;
			} catch (NumberFormatException e) {}
    	}
    	for (int i = 1; i < strings.length; i++) {
			String temp = strings[i];
			SQLStatement += " LIKE b.Title = '%" + temp + "%'";
			SQLStatement += " LIKE b.Author = '%" + temp + "%'";
			SQLStatement += " LIKE b.Editor = '%" + temp + "%'";
			SQLStatement += " LIKE b.ISBN = '%" + temp + "%'";
			try {
				Integer year = Integer.parseInt(temp);
				SQLStatement += " OR b.Year = " + year;
			} catch (NumberFormatException e) {}
		}
    	return SQLStatement;
	}
	
}
