package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//1. Get Connection to Database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "");
			
			//2. Create statement
			st = conn.createStatement();
			
			//3. Insert a new Employee
			int rowsAffected = st.executeUpdate(
					"INSERT INTO employees"
					+ "(last_name, first_name, email, department, salary)"
					+ " VALUES"
					+ "('Wright', 'Eric', 'eric.wright@gmail.com', 'IT', 33000.00)"
					);
			System.out.println("Rows affected: " + rowsAffected);
			
			//4. Verify this by getting a list of employees
			rs = st.executeQuery("SELECT * FROM employees ORDER BY last_name");
			
			//Process the result set
			while(rs.next()) {
				System.out.println(rs.getInt("id")+ " " + rs.getString("first_name") + " " + rs.getString("last_name"));
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
