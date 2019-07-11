package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {

	// Database connection details
	private String db = "jdbc:mysql://mydbstockmarketsimulator.c3wmgxpni6z3.eu-west-1.rds.amazonaws.com:3306/master";
	private String un = "master";
	private String pw = "master;1234";

	// Database objects
	private Connection conn;
	private Statement stmt;

	public DataSource() {

		// Establishing connection
		try {

			// Get a connection to the database
			conn = DriverManager.getConnection(db, un, pw);

			// Get a statement from the connection
			stmt = conn.createStatement();

		} catch (SQLException se) {
			System.out.println("SQL Exception:");

			// Loop through the SQL Exceptions
			while (se != null) {
				System.out.println("State  : " + se.getSQLState());
				System.out.println("Message: " + se.getMessage());
				System.out.println("Error  : " + se.getErrorCode());

				se = se.getNextException();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Method to execute any SELECT query
	public ResultSet select(String query) {
		// Execute the query
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	// Method to execute any INSERT statement
	public boolean save(String query) {

		try {
			stmt.execute(query);
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	// AND FINALLY WE HAVE A SEPARATE METHOD TO
	// CLOSE THE STATEMENT AND THE CONNECTION
	public void closing() {
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
