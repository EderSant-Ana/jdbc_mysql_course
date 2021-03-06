package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBException.DBException;

public class PrepareStatement {
	public static void main(String [] args) throws SQLException {
	
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "");
			st = conn.prepareStatement("SELECT * FROM employees WHERE salary > ? AND department = ? ORDER BY first_name");
			
			st.setDouble(1, 80000.00);
			st.setString(2, "Legal");
			
			rs = st.executeQuery();
			
			display(rs);	
			
			st.setDouble(1, 100000);
			st.setString(2, "HR");
			
			rs = st.executeQuery();
			
			System.out.println();
			display(rs);
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
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
			System.out.println("Close connection");
		}	
	}
	
	public static void display(ResultSet rs) throws SQLException {
		while(rs.next()) {
			System.out.println(rs.getString("first_name") + ", " + rs.getString("last_name") +
					", " + rs.getDouble("salary") + ", " + rs.getString("department"));			
		}
	}
}
