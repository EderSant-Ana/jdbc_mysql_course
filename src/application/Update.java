package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
	public static void main(String[] args) throws SQLException {
	
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "");
		
		st = conn.createStatement();
		int rowsAffected = st.executeUpdate(
				"UPDATE employees SET" +
				" email = 'john@gmail.com'" +
				" WHERE last_name = 'Doe' AND first_name = 'John'"				
				);
		
		System.out.println("Rows Affected " + rowsAffected);
		
		rs = st.executeQuery("SELECT * FROM employees");
		
		while(rs.next()) {
			if(rs.getString("email").equals("john@gmail.com")) {
				System.out.println(rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("last_name")  
        			+ " " + rs.getString("email") + " " + rs.getString("department") + " " + rs.getDouble("salary"));
			}
		}			
	} 
	catch(SQLException e){
		e.printStackTrace();
	}
	finally {
		if(rs != null) {
			rs.close();
		}
		if(st != null) {
			st.close();
		}
		if(conn != null) {
			conn.close();
		}		
		}	
	}
}
