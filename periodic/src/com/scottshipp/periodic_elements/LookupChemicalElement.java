package com.scottshipp.periodic_elements;

import java.sql.*;


public class LookupChemicalElement {
	
	public static ChemicalElement by(SearchOptions searchCriteria, String value) {
		ChemicalElement elem = new ChemicalElement();
		try {
			elem = getElementFromDb(searchCriteria, value);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return elem;
	}
	
	private static ChemicalElement getElementFromDb(SearchOptions column, String value) throws SQLException, ClassNotFoundException {
		ChemicalElement e = new ChemicalElement();
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
            getConnection("jdbc:h2:~/test", "sa", "");
        String SQLQuery = String.format("Select * from CSVREAD('http://localhost/periodic/periodic_table_of_elements.csv') WHERE LOWER(%s) LIKE ?", column.getColumnName());
        PreparedStatement getAnElementRow = conn.prepareStatement(SQLQuery);
        getAnElementRow.setString(1, String.format("%s%s%s", "%", value, "%"));
        ResultSet theElementSet = getAnElementRow.executeQuery();
        if(theElementSet.next()) {
        	e = new ChemicalElement(
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
