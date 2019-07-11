package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import investor.Investor;

public class MySQLInvestorDAO implements InvestorDAO{
	
	// creates an array list to store all investor
	private ArrayList<Investor.InvestorBuilder> investor = new ArrayList<Investor.InvestorBuilder>();
	
	// Create an static private instance
	// Singleton pattern
	static private MySQLInvestorDAO instance = new MySQLInvestorDAO();
	
	
	// GET ALL COMPANIES
		@Override
		public ArrayList<Investor.InvestorBuilder> getInvestors() {
			
			//creates an array list to store all companies
			ArrayList<Investor.InvestorBuilder> investor = new ArrayList<Investor.InvestorBuilder>();
			
			// creates the query
			String query = "SELECT * FROM Investor";
			
			// accessing the database
			DataSource db = new DataSource();
			
			// querying the database
			ResultSet rs = db.select(query);
			
			// loop over the result set
			try {
				while(rs.next()) {
					int id = rs.getInt(1);
					String firstName = rs.getString(2);
					String surname = rs.getString(3);
					String phone = rs.getString(4);
					String dob = rs.getString(5);
					double budget = rs.getDouble(6);
					
					investor.add(new Investor.InvestorBuilder(id, firstName, surname, phone, dob, budget));
				}
				
				db.closing();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return investor;
		}
		
		
		@Override
		public boolean saveInvestor(Investor.InvestorBuilder investor) {
			
			// accessing the database
			DataSource db = new DataSource();
			
			// getting data from object
			int id = investor.build().getId();
			String firstName = investor.build().getFirstName();
			String surname = investor.build().getSurname();
			String phone = investor.build().getPhone();
			String dob = investor.build().getDob();
			double budget = investor.build().getBudget();
			
			// creating the query
			String query = "INSERT INTO Investor (id, firstName, surname, phone, dob, budget) values ('"+ id + "', '" + firstName + "', '" + surname + "', '" + phone + "', '" + dob + "', '" + budget +"')";
			
			// save the data
			boolean result = db.save(query);
					
			// closing database
			db.closing();
					
			return result;
		}
		
		
		// Added a static public getter for the instance
		public static MySQLInvestorDAO getInstance() {
			return instance;
		}
}
