package SetUp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import DAO.CompanyDAO;
import DAO.InvestorDAO;
import DAO.MySQLCompanyDAO;
import DAO.MySQLInvestorDAO;
import investor.Investor;
import investor.Investor.InvestorBuilder;
import company.Company;
import company.Company.CompanyBuilder;

public class SetUpDb {

	int amtOfCompanies = 100;
	int amtOfInvestors = 100;

	public StoredData sd = new StoredData();

	public static ArrayList<Company.CompanyBuilder> listOfCompany = new ArrayList();

	public static ArrayList<Investor.InvestorBuilder> listOfInvestor = new ArrayList();

	Random r = new Random();
	
	
	// we don't create a new instance, we use the one already created
	// Singleton Pattern
	MySQLCompanyDAO dbCompany = MySQLCompanyDAO.getInstance();
	
	MySQLInvestorDAO dbInvestor = MySQLInvestorDAO.getInstance();

	public void getItReady() {
		
		dbCheck();
	}

	public void dbCheck() {
		
		ArrayList<Company.CompanyBuilder> companies = dbCompany.getCompanies();
		
		ArrayList<Investor.InvestorBuilder> investors = dbInvestor.getInvestors();
		
		// if db is empty we create the data 
		if(investors.isEmpty() && companies.isEmpty()) {
			createData();
			fillDB();
		} 
		else {
			// get data from db
			for(int i = 0; i < companies.size(); i++) {
				Company.CompanyBuilder company = companies.get(i);
				listOfCompany.add(company);
			}
			
			for(int i = 0; i < investors.size(); i++) {
				Investor.InvestorBuilder investor = investors.get(i);
				listOfInvestor.add(investor);
			}
		}
	}
	
	public void createData() {
		
		for (int i = 0; i < amtOfInvestors; i++) {
			listOfInvestor.add(new Investor.InvestorBuilder());
		}

		for (int i = 0; i < amtOfCompanies; i++) {
			listOfCompany.add(new Company.CompanyBuilder());
		}
	}
	
	public void fillDB() {
			
		
		listOfCompany.stream().forEach((Company.CompanyBuilder company) -> {
			company.setName(sd.companyName[r.nextInt(sd.companyName.length)]);
			company.setSector(sd.sector[r.nextInt(sd.sector.length)]);
			
			System.out.println("creating company data...");
			dbCompany.saveCompany(company);
		});
		
		listOfInvestor.stream().forEach((Investor.InvestorBuilder investor) -> {
			investor.setFirstName(sd.firstName[r.nextInt(sd.firstName.length)]);
			investor.setSurname(sd.surname[r.nextInt(sd.surname.length)]);
			investor.setPhone(sd.phone[r.nextInt(sd.phone.length)]);
			investor.setDob(sd.dob[r.nextInt(sd.dob.length)]);
			
			System.out.println("creating investor data...");
			dbInvestor.saveInvestor(investor);
		});
		
		dbCheck();
		
	}

}
