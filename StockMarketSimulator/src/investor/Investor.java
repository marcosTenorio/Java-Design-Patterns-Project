package investor;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import company.Company;

public class Investor {
	
	private static final AtomicInteger idGenerator = new AtomicInteger(1000);
    private final Integer id;
	private double budget; // 1000 - 10000
	private String firstName;
	private String surname;
	private String phone;
	private String dob;
	private int shares;
	
	
	// Defining a private constructor, that takes a builder object,
	// so the client need to instantiate through the builder
	private Investor(InvestorBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.surname = builder.surname;
		this.phone = builder.phone;
		this.dob = builder.dob;
		this.budget = builder.budget;
		this.shares = builder.shares;
	}

	public double getBudget() {
		return budget;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	
	public String getName() {
		return firstName + " " + surname;
	}
	
	public String getPhone() {
		return phone;
	}

	public String getDob() {
		return dob;
	}
	
	public int getShares() {
		return shares;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return "Investor ID: " +this.getId() + " | name: " + this.getName() +  "\n"
				+ "dob: " + this.getDob() + "\n"
				+ "phone: " + this.getPhone() + "\n"
				+ "budget: $"	+ this.getBudget()+ "\n"
				+ "shares: " + this.getShares();
				
	}
	
	
	public static class InvestorBuilder {
		
		private static final AtomicInteger idGenerator = new AtomicInteger(1000);
	    private final Integer id;
		private double budget; // 1000 - 10000
		private String firstName;
		private String surname;
		private String phone;
		private String dob;
		private int shares;
		
		
		private final ArrayList<Company.CompanyBuilder> companies;
		
		Random r = new Random();
		
		int minBudget = 1000;
		int maxBudget = 10000;
		
		public InvestorBuilder() {
			this.budget = r.nextInt(maxBudget-minBudget) + minBudget;
			this.id = idGenerator.getAndIncrement();
			this.companies = new ArrayList<>();
		}
		
		// This is the builder class. This is instantiated without the need
		// of an instance of the Investor class as it is static
		public InvestorBuilder(int id, String firstName, String surname, String phone, String dob, double budget ) {
			this.id = id;
			this.firstName = firstName;
			this.surname = surname;
			this.phone = phone;
			this.dob = dob;
			this.budget = budget;
			this.shares = 0;
			this.companies = new ArrayList<>();
		}
		
		public InvestorBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public InvestorBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}
		
		public InvestorBuilder setName(String firstName, String surname) {
			this.firstName = firstName;
			this.surname = surname;
			return this;
		}
		
		public InvestorBuilder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		
		public InvestorBuilder setDob(String dob) {
			this.dob = dob;
			return this;
		}
		
		public InvestorBuilder setBudget(double budget) {
			this.budget = budget;
			return this;
		}
		
		public void buyShare(double price) {
			this.setBudget(build().getBudget() - price);
			this.shares++;
		}
		
		public void addCompany(Company.CompanyBuilder company) {
			companies.add(company);
		}
		
		public ArrayList<Company.CompanyBuilder> getCompanies() {
			return companies;
		}

		public boolean available(Company.CompanyBuilder company) {
			return !companies.contains(company);
		}
		
		// This is the method to return the actual instance of investor. 
		// And this guy, is in charge of the instantiation process
		public Investor build() {
			return new Investor(this);
		}
	}
	
	
}
