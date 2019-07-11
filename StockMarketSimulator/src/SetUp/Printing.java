package SetUp;

import java.util.ArrayList;

import company.Company;
import investor.Investor;

public class Printing {
	
	ArrayList<Company.CompanyBuilder> highestResults = new ArrayList();
	ArrayList<Company.CompanyBuilder> lowestResults = new ArrayList();
	
	ArrayList<Investor.InvestorBuilder> mostSharesResult = new ArrayList();
	ArrayList<Investor.InvestorBuilder> lowestSharesResult = new ArrayList();
	
	ArrayList<Investor.InvestorBuilder> mostCompaniesResult = new ArrayList();
	ArrayList<Investor.InvestorBuilder> leastCompaniesResult = new ArrayList();
	

	// display all investors
	public void displayInvestors(SetUpDb setData) {

		for (int i = 0; i < setData.listOfInvestor.size(); i++) {
			Investor.InvestorBuilder in = setData.listOfInvestor.get(i);
			System.out.println();
			System.out.println(in.build().toString());
			System.out.println();
		}
	}
	
	// display all companies
	public void displayCompanies(SetUpDb setData) {

		for (int i = 0; i < setData.listOfCompany.size(); i++) {
			Company.CompanyBuilder c = setData.listOfCompany.get(i);
			System.out.println();
			System.out.println(c.build().toString());
			System.out.println();
		}
	}
	
	// get the companies with the highest capital
	public void calculateCapital(SetUpDb setData) {
		double highest = 0;
		double lowest = 10000000;
		
		// get the highest capital between companies
		for(int i = 0; i < setData.listOfCompany.size(); i++) {
			Company.CompanyBuilder c = setData.listOfCompany.get(i);
			double capital = c.build().getShares() * c.build().getSharePrice();
			if (capital > highest) {
				highest = capital;
			}else if(capital < lowest) {
				lowest = capital;
			}
		}
		
		// check if there are more than one company with the highest capital
		for(int i = 0; i < setData.listOfCompany.size(); i++) {
			Company.CompanyBuilder c = setData.listOfCompany.get(i);
			double capital = c.build().getShares() * c.build().getSharePrice();
			if(capital == highest && !highestResults.contains(c)) {
				highestResults.add(c);
			} else if (capital == lowest && !lowestResults.contains(c)) {
				lowestResults.add(c);
			}
		}
		
	}
	
	public void printLowestCapital() {
		System.out.println();
		System.out.println("----- Company(ies) with the lowest capital (shares * shares price) -----");
		for(int i = 0; i < lowestResults.size(); i++) {
			Company.CompanyBuilder c = lowestResults.get(i);
			System.out.println();
			System.out.println();
			System.out.println(c.build().toString());
			System.out.println();
		}
	}
	
	public void printHighestCapital() {
		System.out.println();
		System.out.println("----- Company(ies) with the highest capital (shares * shares price) -----");
		for(int i = 0; i < highestResults.size(); i++) {
			Company.CompanyBuilder c = highestResults.get(i);
			System.out.println();
			System.out.println();
			System.out.println(c.build().toString());
			System.out.println();
		}
	}
	

	
	
	// returns Investor with the highest number of shares
	public void calculateNumberOfShares(SetUpDb setData) {
		int highest = 0;
		int lowest = 10000;
		
		// get the lowest number of shares between the investors
		for(int i = 0; i < setData.listOfInvestor.size(); i++) {
			Investor.InvestorBuilder in = setData.listOfInvestor.get(i);
			int shares = in.build().getShares();
			if (shares > highest) {
				highest = shares;
			} else if (shares < lowest) {
				lowest = shares;
			}
		}
		
		// check if there are more than one investor with the highest number of shares
		for(int i = 0; i < setData.listOfInvestor.size(); i++) {
			Investor.InvestorBuilder in = setData.listOfInvestor.get(i);
			int shares = in.build().getShares();
			if(shares == highest && !mostSharesResult.contains(in)) {
				mostSharesResult.add(in);
			}else if(shares == lowest && !lowestSharesResult.contains(in)) {
				lowestSharesResult.add(in);
			}
		}
	}
	
	public void printHighestSharesInvestor() {
		System.out.println();
		System.out.println("----- Investor(s) with the highest number of shares -----");
		for(int i = 0; i < mostSharesResult.size(); i++) {
			Investor.InvestorBuilder in = mostSharesResult.get(i);
			System.out.println();
			System.out.println();
			System.out.println(in.build().toString());
			System.out.println();
		}
	}
	
	public void printLowestSharesInvestor() {
		System.out.println();
		System.out.println("----- Investor(s) with the lowest number of shares -----");
		for(int i = 0; i < lowestSharesResult.size(); i++) {
			Investor.InvestorBuilder in = lowestSharesResult.get(i);
			System.out.println();
			System.out.println();
			System.out.println(in.build().toString());
			System.out.println();
		}
	}
	
	
	
	
	
	
	public void printCompanies(SetUpDb setData) {
		int higher = 0;
		int lowest = 2000;
				
		for(int i = 0; i < setData.listOfInvestor.size(); i++) {
			Investor.InvestorBuilder in = setData.listOfInvestor.get(i);
			int size = in.getCompanies().size();
			if(size > higher) {
				higher = size;
			}else if (size < lowest) {
				lowest = size;
			}
		}
		
		for(int i = 0; i < setData.listOfInvestor.size(); i++) {
			Investor.InvestorBuilder in = setData.listOfInvestor.get(i);
			int size = in.getCompanies().size();
			if(size == higher && !mostCompaniesResult.contains(in)) {
				mostCompaniesResult.add(in);
			}else if(size == lowest && !leastCompaniesResult.contains(in)) {
				leastCompaniesResult.add(in);
			}
		}
	}
	
	public void printMostCompanies() {
		System.out.println();
		System.out.println("----- Investor(s) that has invested in the most companies -----");
		for(int i = 0; i < mostCompaniesResult.size(); i++) {
			Investor.InvestorBuilder in = mostCompaniesResult.get(i);
			System.out.println();
			System.out.println();
			System.out.println(in.build().toString());
			System.out.println();
		}
	}
	
	public void printLeastCompanies() {
		System.out.println();
		System.out.println("----- Investor(s) that has invested in the most companies -----");
		for(int i = 0; i < leastCompaniesResult.size(); i++) {
			Investor.InvestorBuilder in = leastCompaniesResult.get(i);
			System.out.println();
			System.out.println();
			System.out.println(in.build().toString());
			System.out.println();
		}
	}
	
	
}
