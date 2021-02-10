package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
	public static void main(String [] args) throws SQLException {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "");
			
			st = conn.createStatement();
			int rowsAffected = st.executeUpdate(
					"DELETE FROM employees " +
					"WHERE last_name = 'Wright' AND first_name = 'Eric'"
					);
			System.out.println("Rows affected: " +  rowsAffected);
			
			rs = st.executeQuery("SELECT * FROM employees");
			
			while(rs.next()) {
				if(rs.getString("last_name") == "Doe" || rs.getString("first_name") == "John") {
					System.out.println("Employee finded: " + rs.getString("last_name") + ", " + rs.getString("first_name"));
				}
				else {
					System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
				}
			}
			
		} catch (SQLException e) {
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
