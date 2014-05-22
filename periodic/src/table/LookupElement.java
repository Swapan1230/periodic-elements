package table;

import java.sql.*;


public class LookupElement {
	
	public static Element by(SearchOptions searchCriteria, String value) {
		Element elem = new Element();
		try {
			elem = getElementFromDb(searchCriteria, value);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return elem;
	}
	
	private static Element getElementFromDb(SearchOptions column, String value) throws SQLException, ClassNotFoundException {
		Element e = new Element();
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
            getConnection("jdbc:h2:~/test", "sa", "");
        String SQLQuery = String.format("Select * from CSVREAD('http://localhost/periodic/periodic_table_of_elements.csv') WHERE LOWER(%s) LIKE ?", column.getColumnName());
        System.out.printf("%nSQL: %n%s%n", SQLQuery);
        System.out.printf("Value: %s%n", value);
        PreparedStatement getAnElementRow = conn.prepareStatement(SQLQuery);
        getAnElementRow.setString(1, String.format("%s%s%s", "%", value, "%"));
        ResultSet theElementSet = getAnElementRow.executeQuery();
        if(theElementSet.next()) {
        	e = new Element(
        			theElementSet.getString(SearchOptions.NAME.getColumnName()), 
        			theElementSet.getString(SearchOptions.ATOMIC_NUMBER.getColumnName()), 
        			theElementSet.getString(SearchOptions.SYMBOL.getColumnName()), 
        			theElementSet.getString(SearchOptions.METAL_GROUP.getColumnName())
        					);
        }
        conn.close();
        return e;
	}

}
