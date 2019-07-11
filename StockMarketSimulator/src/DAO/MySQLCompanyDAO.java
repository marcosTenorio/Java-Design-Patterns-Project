package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import company.Company;
import company.Company.CompanyBuilder;

public class MySQLCompanyDAO implements CompanyDAO{
	
	
	//creates an array list to store all companies
	ArrayList<Company.CompanyBuilder> company = new ArrayList<Company.CompanyBuilder>();
	
	
	// Create an static private instance
	// Singleton pattern
	static private MySQLCompanyDAO instance = new MySQLCompanyDAO();
	
	// GET ALL COMPANIES
		@Override
		public ArrayList<Company.CompanyBuilder> getCompanies() {
			
			
			// creates the query
			String query = "SELECT * FROM Company";
			
			// accessing the database
			DataSource db = new DataSource();
			
			// querying the database
			ResultSet rs = db.select(query);
			
			// loop over the result set
			try {
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String sector = rs.getString(3);
					int shares = rs.getInt(4);
					double sharePrice = rs.getDouble(5);
					
					company.add(new Company.CompanyBuilder(id, name, sector, shares, sharePrice));
				}
				
				db.closing();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return company;
		}
		
		
		@Override
		public boolean saveCompany(Company.CompanyBuilder company) {
			
			// accessing the database
			DataSource db = new DataSource();
			
			// getting data from object
			int id = company.build().getId();
			String name = company.build().getName();
			String sector = company.build().getSector();
			int shares = company.build().getShares();
			double sharePrice = company.build().getSharePrice();
			
			// creating the query
			String query = "INSERT INTO Company (id, name, sector, shares, sharePrice) values ('"+ id + "', '" + name + "', '" + sector + "', '" + shares + "', '" + sharePrice + "')";
			
			// save the data
			boolean result = db.save(query);
					
			// closing database
			db.closing();
					
			return result;
		}
		
		// Added a static public getter for the instance
		public static MySQLCompanyDAO getInstance() {
			return instance;
		}
}
