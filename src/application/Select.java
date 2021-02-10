package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Employee;

public class Select {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//1. Get a connection to database:
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "");
			System.out.println("Database connection successfull!");
			
			//2. Create statement
			st = conn.createStatement();
			
			//3. Execute SQL query
			rs = st.executeQuery("SELECT * FROM employees");
			//rs = st.executeQuery("SELECT * FROM employees WHERE salary > 90000.00");
			
			//4. Process the result set
			List<Employee> list = new ArrayList<>();
			while(rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("last_name") + ", " + rs.getString("first_name")
				 + ", " + rs.getString("email") + ", " + rs.getString("department") + ", " + rs.getDouble("salary"));
				
				if(rs.getDouble("salary") > 90000.00) {
				list.add(new Employee(rs.getInt("id"), rs.getString("last_name"),  rs.getString("first_name"), rs.getDouble("salary")));
				}
			}		
			System.out.println();
			for(Employee e: list) {
				System.out.println(e);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				rs.close();
			}
			if(conn != null) {
				rs.close();
			}
			System.out.println("Close connection");
		}

	}

}
